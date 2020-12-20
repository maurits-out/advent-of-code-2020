package day18

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 18: Operation Order")
class OperationOrderTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val example = """
                1 + (2 * 3) + (4 * (5 + 6))
                2 * 3 + (4 * 5)
                5 + (8 * 3 + 9 + 3 * 4 * 3)
                5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))
                ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2
            """.trimIndent().lines()
            val answer = OperationOrder(example).part1()
            assertEquals(26386, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = OperationOrder(readInput()).part1()
            assertEquals(6923486965641, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val example = """
                1 + (2 * 3) + (4 * (5 + 6))
                2 * 3 + (4 * 5)
                5 + (8 * 3 + 9 + 3 * 4 * 3)
                5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))
                ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2
            """.trimIndent().lines()
            val answer = OperationOrder(example).part2()
            assertEquals(693942, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = OperationOrder(readInput()).part2()
            assertEquals(70722650566361, answer)
        }
    }

    private fun readInput(): List<String> = common.readInputAsLines("day18/input.txt")
}