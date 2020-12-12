package day11

import kotlin.math.abs
import kotlin.math.sign

class SeatingSystem(private val initialSeatLayout: List<String>) {

    private val seatCoordinates = extractSeatCoordinates()

    fun part1(): Int = simulateSeatingAreaPart1(emptySet(), 0)

    fun part2(): Int = simulateSeatingAreaPart2(emptySet(), 0)

    private fun extractSeatCoordinates(): Set<Pair<Int, Int>> {
        return initialSeatLayout
            .withIndex()
            .flatMap { row ->
                row.value
                    .withIndex()
                    .filter { it.value == 'L' }
                    .map { column -> row.index to column.index }
            }.toSet()
    }

    private tailrec fun simulateSeatingAreaPart1(occupied: Set<Pair<Int, Int>>, count: Int): Int {
        val predicate: (Pair<Int, Int>) -> Boolean = { adjacentOccupiedSeats(occupied, it).isEmpty() }
        val newOccupied = seatCoordinates
            .minus(occupied)
            .filterTo(HashSet(), predicate)
        val newEmpty = occupied
            .filterTo(HashSet(), { adjacentOccupiedSeats(occupied, it).size >= 4 })
        val next = occupied.union(newOccupied).minus(newEmpty)

        if (next == occupied) {
            return occupied.size
        }
        return simulateSeatingAreaPart1(next, count + 1)
    }

    private tailrec fun simulateSeatingAreaPart2(occupied: Set<Pair<Int, Int>>, count: Int): Int {
        val newOccupied = seatCoordinates
            .minus(occupied)
            .filterTo(HashSet()) { occupiedAndVisibleSeatsInAnyDirection(occupied, it).isEmpty() }
        val newEmpty = occupied
            .filterTo(HashSet(), { occupiedAndVisibleSeatsInAnyDirection(occupied, it).size >= 5 })
        val next = occupied.union(newOccupied).minus(newEmpty)

        if (next == occupied) {
            return occupied.size
        }
        return simulateSeatingAreaPart2(next, count + 1)
    }

    private fun adjacentOccupiedSeats(occupied: Set<Pair<Int, Int>>, seat: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val (row, column) = seat
        return setOf(
            row - 1 to column - 1,
            row - 1 to column,
            row - 1 to column + 1,
            row to column - 1,
            row to column + 1,
            row + 1 to column - 1,
            row + 1 to column,
            row + 1 to column + 1
        ).intersect(occupied)
    }

    private fun occupiedAndVisibleSeatsInAnyDirection(occupied: Set<Pair<Int, Int>>, seat: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val (row, column) = seat
        return occupied.filter { (r, c) ->
            (row == r && column != c) ||
                    (row != r && column == c) ||
                    (abs(row - r) == abs(column - c) && row != r)
        }.filterTo(HashSet()) { isVisible(it, seat) }
    }

    private tailrec fun isVisible(from: Pair<Int, Int>, to: Pair<Int, Int>): Boolean {
        val nextSeat = move(from, to)
        if (nextSeat == to) {
            return true
        }
        if (nextSeat in seatCoordinates) {
            return false
        }
        return isVisible(nextSeat, to)
    }

    private fun move(from: Pair<Int, Int>, to: Pair<Int, Int>): Pair<Int, Int> {
        val (r1, c1) = from
        val (r2, c2) = to
        return r1 + delta(r1, r2) to c1 + delta(c1, c2)
    }

    private fun delta(x: Int, y: Int) = sign((y - x).toDouble()).toInt()
}
