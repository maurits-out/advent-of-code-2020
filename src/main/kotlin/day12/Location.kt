package day12

import kotlin.math.abs

data class Location(val north: Int, val east: Int) {
    fun manhattanDistance(): Int = abs(north) + abs(east)
}