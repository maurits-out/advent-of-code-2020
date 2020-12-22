package day20

class JurassicJigsaw(input: String) {

    private val allTiles = input.split("\n\n").map { parseTile(it) }

    fun part1(): Long {
        return allTiles
            .filter { tile -> matchingEdges(tile).size == 2 }
            .fold(1L) { acc, tile -> acc * tile.id }
    }

    private fun matchingEdges(tile: Tile): Set<String> =
        allTiles
            .filterNot { tile.id == it.id }
            .fold(emptySet()) { acc, other -> acc.union(matchingEdges(tile, other)) }

    private fun matchingEdges(tile1: Tile, tile2: Tile): Set<String> =
        tile1.edges.filter { edgeMatchesAnyEdgeFromTile(it, tile2) }.toSet()

    private fun edgeMatchesAnyEdgeFromTile(edge: String, tile: Tile): Boolean =
        tile.edges.any { edge == it || edge.reversed() == it }

    private fun parseTile(input: String): Tile {
        val lines = input.lines()
        val id = lines.first().substring(lines[0].indexOf(' ') + 1, lines[0].indexOf(':')).toInt()
        val image = lines.drop(1)
        return Tile(id, image)
    }

    data class Tile(val id: Int, val image: List<String>) {

        val edges by lazy {
            listOf(
                image[0],
                image[image.size - 1],
                image.joinToString(separator = "") { row -> row.subSequence(0, 1) },
                image.joinToString(separator = "") { row -> row.subSequence(row.length - 1, row.length) }
            )
        }
    }
}
