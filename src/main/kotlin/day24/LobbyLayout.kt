package day24

import day24.LobbyLayout.Direction.*

typealias Tile = Pair<Int, Int>

class LobbyLayout(private val input: String) {

    private val regex = Regex("e|se|sw|w|nw|ne")

    enum class Direction { E, SE, SW, W, NW, NE }

    fun part1(): Int =
        input.lines().map { applySteps(parse(it)) }.groupBy { it }.count { it.value.size % 2 == 1 }

    fun part2(): Int =
        (1..100).fold(initialBlackTiles()) { black, _ -> applyRules(black) }.size

    private fun applyRules(black: Set<Tile>): Set<Tile> = black.minus(blackToWhite(black)).union(whiteToBlack(black))

    private fun blackToWhite(black: Set<Tile>): Set<Tile> =
        black.filterTo(HashSet()) { it.neighbors().intersect(black).count() !in listOf(1, 2) }

    private fun whiteToBlack(black: Set<Tile>): Set<Tile> =
        black.flatMap { it.neighbors() }
            .filterTo(HashSet()) { it !in black && it.neighbors().intersect(black).count() == 2 }

    private fun initialBlackTiles(): Set<Tile> = input
        .lines()
        .map { applySteps(parse(it)) }
        .groupBy { it }
        .filterValues { it.size % 2 == 1 }
        .keys

    private fun applySteps(steps: Sequence<Direction>): Tile =
        steps.fold(0 to 0) { tile, direction -> tile.neighbor(direction) }

    private fun Tile.neighbors(): Set<Tile> = Direction.values().mapTo(HashSet()) { this.neighbor(it) }

    private fun Tile.neighbor(direction: Direction): Tile {
        val (x, y) = this
        if (y % 2 == 0) {
            return when (direction) {
                E -> x + 1 to y
                W -> x - 1 to y
                SE -> x to y + 1
                SW -> x - 1 to y + 1
                NE -> x to y - 1
                NW -> x - 1 to y - 1
            }
        }
        return when (direction) {
            E -> x + 1 to y
            W -> x - 1 to y
            SE -> x + 1 to y + 1
            SW -> x to y + 1
            NE -> x + 1 to y - 1
            NW -> x to y - 1
        }
    }

    private fun parse(line: String): Sequence<Direction> =
        regex.findAll(line).map { Direction.valueOf(it.value.toUpperCase()) }
}
