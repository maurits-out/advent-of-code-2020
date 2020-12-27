package day11

import kotlin.math.abs
import kotlin.math.sign

typealias Seat = Pair<Int,Int>

class SeatingSystem(private val initialSeatLayout: List<String>) {

    private val seatCoordinates = extractSeatCoordinates()

    fun part1(): Int = simulateSeatingAreaPart1(emptySet(), 0)

    fun part2(): Int = simulateSeatingAreaPart2(emptySet(), 0)

    private fun extractSeatCoordinates(): Set<Seat> {
        return initialSeatLayout
            .withIndex()
            .flatMap { row ->
                row.value
                    .withIndex()
                    .filter { it.value == 'L' }
                    .map { column -> row.index to column.index }
            }.toSet()
    }

    private tailrec fun simulateSeatingAreaPart1(occupied: Set<Seat>, count: Int): Int {
        val newOccupied = seatCoordinates
            .minus(occupied)
            .filterTo(HashSet()) { adjacentOccupiedSeats(occupied, it).isEmpty() }
        val newEmpty = occupied
            .filterTo(HashSet(), { adjacentOccupiedSeats(occupied, it).size >= 4 })
        val next = occupied.union(newOccupied).minus(newEmpty)

        if (next == occupied) {
            return occupied.size
        }
        return simulateSeatingAreaPart1(next, count + 1)
    }

    private tailrec fun simulateSeatingAreaPart2(occupied: Set<Seat>, count: Int): Int {
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

    private fun adjacentOccupiedSeats(occupied: Set<Seat>, seat: Seat): Set<Seat> {
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

    private fun occupiedAndVisibleSeatsInAnyDirection(occupied: Set<Seat>, seat: Seat): Set<Seat> {
        val (row, column) = seat
        return occupied.filterTo(HashSet()) { (r, c) ->
            (row == r && column != c) ||
                    (row != r && column == c) ||
                    (abs(row - r) == abs(column - c) && row != r)
        }.filterTo(HashSet()) { isVisible(it, seat) }
    }

    private tailrec fun isVisible(from: Seat, to: Seat): Boolean {
        val nextSeat = move(from, to)
        if (nextSeat == to) {
            return true
        }
        if (nextSeat in seatCoordinates) {
            return false
        }
        return isVisible(nextSeat, to)
    }

    private fun move(from: Seat, to: Seat): Seat {
        val (r1, c1) = from
        val (r2, c2) = to
        return r1 + delta(r1, r2) to c1 + delta(c1, c2)
    }

    private fun delta(x: Int, y: Int) = sign((y - x).toDouble()).toInt()
}
