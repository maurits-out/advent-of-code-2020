package day20

import day20.JurassicJigsaw.EdgeType.*

typealias Image = Array<CharArray>

class JurassicJigsaw(input: String) {

    private val allTiles: List<Pair<Int, Image>> = input.split("\n\n").flatMap { parseTile(it) }

    fun part1(): Long {
        val tiles = fixJigsaw()
        return tiles[0][0].first.toLong() * tiles[0][11].first * tiles[11][0].first * tiles[11][11].first
    }

    fun part2(): Int {
        val tiles = fixJigsaw()
        val overallImages = rotateAndFlipOverallImage(createOverallImage(tiles))
        overallImages.forEach { image -> markSeaMonsters(image) }
        return overallImages.minOf { image -> image.sumBy { row -> row.count { it == '#' } } }
    }

    private fun markSeaMonsters(image: Image) {
        val seaMonster = listOf(
            "                  # ".toCharArray(),
            "#    ##    ##    ###".toCharArray(),
            " #  #  #  #  #  #   ".toCharArray()
        ).toTypedArray()
        for (r in 0 until image.size - seaMonster.size + 1) {
            for (c in 0 until image[r].size - seaMonster[0].size + 1) {
                if (hasSeaMonster(image, seaMonster, r, c)) {
                    markSeaMonster(image, seaMonster, r, c)
                }
            }
        }
    }

    private fun hasSeaMonster(image: Image, seaMonster: Image, topRow: Int, topColumn: Int): Boolean {
        for (r in seaMonster.indices) {
            for (c in seaMonster[r].indices) {
                if (seaMonster[r][c] == '#') {
                    if (image[topRow + r][topColumn + c] != '#') {
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun markSeaMonster(image: Image, seaMonster: Image, topRow: Int, topColumn: Int) {
        for (r in seaMonster.indices) {
            for (c in seaMonster[r].indices) {
                if (seaMonster[r][c] == '#') {
                    image[topRow + r][topColumn + c] = 'O'
                }
            }
        }
    }

    private fun rotateAndFlipOverallImage(overallImage: Image): List<Image> {
        return listOf(overallImage, flip(overallImage))
            .flatMap { image ->
                (0 until 4).map { times ->
                    image to times
                }
            }.map { (image, times) ->
                rotate(image, times)
            }
    }

    private fun fixJigsaw(): List<List<Pair<Int, Image>>> {
        val tiles = (0 until 12).map { mutableListOf<Pair<Int, Image>>() }
        val topLeft = allTiles.first { tile -> isTopLeft(tile.first, tile.second) }
        tiles[0].add(topLeft)
        for (c in 1 until 12) {
            val prev = tiles[0][c - 1]
            tiles[0].add(findTileNextTo(prev.first, prev.second))
        }
        for (r in 1 until 12) {
            val above = tiles[r - 1][0]
            tiles[r].add(findTileBelow(above.first, above.second))
            for (c in 1 until 12) {
                val prev = tiles[r][c - 1]
                tiles[r].add(findTileNextTo(prev.first, prev.second))
            }
        }
        return tiles
    }

    private fun createOverallImage(tiles: List<List<Pair<Int, Image>>>): Image {
        val result = (0 until 96).map { CharArray(96) { ' ' } }.toTypedArray()
        for (r in tiles.indices) {
            for (c in tiles[r].indices) {
                val image = removeBorder(tiles[r][c].second)
                copyImageInto(image, result, 8 * r, 8 * c)
            }
        }
        return result
    }

    private fun copyImageInto(image: Array<CharArray>, overallImage: Array<CharArray>, topRow: Int, topColumn: Int) {
        for (r in image.indices) {
            for (c in image[r].indices) {
                overallImage[topRow + r][topColumn + c] = image[r][c]
            }
        }
    }

    private fun removeBorder(image: Image): Image {
        return image.drop(1).dropLast(1).map { row ->
            row.drop(1).dropLast(1).toCharArray()
        }.toTypedArray()
    }

    private fun findTileNextTo(tileID: Int, image: Image): Pair<Int, Image> {
        return allTiles.single { other ->
            other.first != tileID && identicalEdges(edge(RIGHT, image), edge(LEFT, other.second))
        }
    }

    private fun findTileBelow(tileID: Int, image: Image): Pair<Int, Image> {
        return allTiles.single { other ->
            other.first != tileID && identicalEdges(edge(BOTTOM, image), edge(TOP, other.second))
        }
    }

    private fun isTopLeft(tileID: Int, image: Image): Boolean {
        return allTiles.any { other ->
            other.first != tileID && identicalEdges(edge(RIGHT, image), edge(LEFT, other.second))
        } && allTiles.any { other ->
            other.first != tileID && identicalEdges(edge(BOTTOM, image), edge(TOP, other.second))
        } && allTiles.none { other ->
            other.first != tileID && identicalEdges(edge(TOP, image), edge(BOTTOM, other.second))
        } && allTiles.none { other ->
            other.first != tileID && identicalEdges(edge(LEFT, image), edge(RIGHT, other.second))
        }
    }

    private fun identicalEdges(edge1: CharArray, edge2: CharArray) = edge1.contentEquals(edge2)

    private fun edge(edgeType: EdgeType, image: Image) = when (edgeType) {
        RIGHT -> image.map { row -> row[row.size - 1] }.toCharArray()
        LEFT -> image.map { row -> row[0] }.toCharArray()
        TOP -> image[0]
        BOTTOM -> image[image.size - 1]
    }

    private fun parseTile(input: String): List<Pair<Int, Image>> {
        val lines = input.lines()
        val id = lines.first().substring(lines[0].indexOf(' ') + 1, lines[0].indexOf(':')).toInt()
        return parseImage(id, lines.drop(1))
    }

    enum class EdgeType {
        LEFT, RIGHT, TOP, BOTTOM
    }

    private fun parseImage(id: Int, lines: List<String>): List<Pair<Int, Image>> {
        val original = lines.map { it.toCharArray() }.toTypedArray()
        return listOf(original, flip(original))
            .flatMap { image ->
                (0 until 4).map { times ->
                    image to times
                }
            }.map { (image, times) ->
                id to rotate(image, times)
            }
    }

    private fun rotate(original: Image): Image {
        val size = original.size
        val rotated = (0 until size).map { CharArray(size) { '.' } }.toTypedArray()
        (0 until size).flatMap { r ->
            (0 until size).map { c ->
                r to c
            }
        }.forEach { (r, c) ->
            rotated[c][size - 1 - r] = original[r][c]
        }
        return rotated
    }

    private fun rotate(original: Image, times: Int): Image =
        (0 until times).fold(original) { image, _ -> rotate(image) }

    private fun flip(original: Image): Image = original.reversedArray()
}
