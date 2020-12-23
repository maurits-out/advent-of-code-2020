package day20

import day20.Tile.EdgeType.*

typealias Image = Array<CharArray>

class JurassicJigsaw(input: String) {

    private val allTiles: List<Tile> = input.split("\n\n").flatMap { parseTile(it) }

    fun part1(): Long {
        val tiles = fixJigsaw()
        return tiles[0][0].id.toLong() * tiles[0][11].id * tiles[11][0].id * tiles[11][11].id
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

    private fun fixJigsaw(): List<List<Tile>> {
        val tiles = (0 until 12).map { mutableListOf<Tile>() }
        val topLeft = allTiles.first { tile -> isTopLeft(tile) }
        tiles[0].add(topLeft)
        for (c in 1 until 12) {
            tiles[0].add(findTileNextTo(tiles[0][c - 1]))
        }
        for (r in 1 until 12) {
            tiles[r].add(findTileBelow(tiles[r - 1][0]))
            for (c in 1 until 12) {
                tiles[r].add(findTileNextTo(tiles[r][c - 1]))
            }
        }
        return tiles
    }

    private fun createOverallImage(tiles: List<List<Tile>>): Image {
        val result = (0 until 96).map { CharArray(96) { ' ' } }.toTypedArray()
        for (r in tiles.indices) {
            for (c in tiles[r].indices) {
                val image = removeBorder(tiles[r][c].image)
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

    private fun findTileNextTo(tile: Tile): Tile = allTiles.single { tile.fitsTo(it, RIGHT) }

    private fun findTileBelow(tile: Tile): Tile = allTiles.single { tile.fitsTo(it, BOTTOM) }

    private fun isTopLeft(tile: Tile): Boolean =
        allTiles.any { tile.fitsTo(it, RIGHT) } && allTiles.any { tile.fitsTo(it, BOTTOM) }
                && allTiles.none { tile.fitsTo(it, TOP) } && allTiles.none { tile.fitsTo(it, LEFT) }

    private fun parseTile(input: String): List<Tile> {
        val lines = input.lines()
        val id = lines.first().substring(lines[0].indexOf(' ') + 1, lines[0].indexOf(':')).toInt()
        return parseImage(id, lines.drop(1))
    }

    private fun parseImage(id: Int, lines: List<String>): List<Tile> {
        val original = lines.map { it.toCharArray() }.toTypedArray()
        return listOf(original, flip(original)).flatMap { image ->
            (0 until 4).map { times ->
                Tile(id, rotate(image, times))
            }
        }
    }

    private fun rotate(original: Image): Image {
        val size = original.size
        val rotated = (0 until size).map { CharArray(size) { '.' } }.toTypedArray()
        (0 until size).map { r ->
            (0 until size).forEach { c ->
                rotated[c][size - 1 - r] = original[r][c]
            }
        }
        return rotated
    }

    private fun rotate(original: Image, times: Int): Image =
        (0 until times).fold(original) { image, _ -> rotate(image) }

    private fun flip(original: Image): Image = original.reversedArray()
}
