package day11

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 11: Seating System")
class SeatingSystemTest {

    private val example = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = SeatingSystem(example).part1()
            assertEquals(37, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = SeatingSystem(readInitialSeatLayout()).part1()
            assertEquals(2178, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = SeatingSystem(example).part2()
            assertEquals(26, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = SeatingSystem(readInitialSeatLayout()).part2()
            assertEquals(1978, answer)
        }
    }

    private fun readInitialSeatLayout() = readInputAsLines("day11/input.txt")
}