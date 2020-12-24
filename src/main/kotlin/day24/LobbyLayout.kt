package day24

import day24.LobbyLayout.Direction.*

typealias Tile = Triple<Int, Int, Int>

class LobbyLayout(private val input: String) {

    private val regex = Regex("e|se|sw|w|nw|ne")

    enum class Direction { E, SE, SW, W, NW, NE }

    fun part1(): Int =
        input.lines().map { applySteps(parse(it)) }.groupBy { it }.count { it.value.size.isOdd() }

    fun part2(): Int =
        (1..100).fold(initialBlackTiles()) { black, _ -> applyRules(black) }.size

    private fun applyRules(black: Set<Tile>): Set<Tile> = black.minus(blackToWhite(black)).union(whiteToBlack(black))

    private fun blackToWhite(black: Set<Tile>): Set<Tile> =
        black.filterTo(HashSet()) { it.neighbors().intersect(black).count() !in listOf(1, 2) }

    private fun whiteToBlack(black: Set<Tile>): Set<Tile> =
        black.flatMapTo(HashSet()) { it.neighbors() }
            .filterTo(HashSet()) { it !in black && it.neighbors().intersect(black).count() == 2 }

    private fun initialBlackTiles(): Set<Tile> = input
        .lines()
        .map { applySteps(parse(it)) }
        .groupBy { it }
        .filterValues { it.size.isOdd() }
        .keys

    private fun applySteps(steps: Sequence<Direction>): Tile =
        steps.fold(Triple(0, 0, 0)) { tile, direction -> tile.neighbor(direction) }

    private fun Tile.neighbors(): Set<Tile> = Direction.values().mapTo(HashSet()) { this.neighbor(it) }

    private fun Tile.neighbor(direction: Direction): Tile {
        val (x, y, z) = this
        return when (direction) {
            E -> Triple(x + 1, y - 1, z)
            W -> Triple(x - 1, y + 1, z)
            SE -> Triple(x, y - 1, z + 1)
            SW -> Triple(x - 1, y, z + 1)
            NE -> Triple(x + 1, y, z - 1)
            NW -> Triple(x, y + 1, z - 1)
        }
    }

    private fun Int.isOdd() = this % 2 == 1

    private fun parse(line: String): Sequence<Direction> =
        regex.findAll(line).map { Direction.valueOf(it.value.toUpperCase()) }
}
