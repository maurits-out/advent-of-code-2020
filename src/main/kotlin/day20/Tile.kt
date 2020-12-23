package day20

import day20.Tile.EdgeType.*

class Tile(val id: Int, val image: Image) {

    enum class EdgeType { LEFT, RIGHT, TOP, BOTTOM }

    fun fitsTo(other: Tile, edgeType: EdgeType): Boolean {
        return id != other.id && when (edgeType) {
            RIGHT -> fits(edge(RIGHT), other.edge(LEFT))
            LEFT -> fits(edge(LEFT), other.edge(RIGHT))
            TOP -> fits(edge(TOP), other.edge(BOTTOM))
            BOTTOM -> fits(edge(BOTTOM), other.edge(TOP))
        }
    }

    private fun edge(edgeType: EdgeType) = when (edgeType) {
        RIGHT -> image.map { it[it.size - 1] }.toCharArray()
        LEFT -> image.map { it[0] }.toCharArray()
        TOP -> image[0]
        BOTTOM -> image[image.size - 1]
    }

    private fun fits(edge1: CharArray, edge2: CharArray) = edge1.contentEquals(edge2)
}