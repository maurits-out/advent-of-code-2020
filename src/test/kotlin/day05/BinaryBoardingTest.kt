package day05

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 5: Binary Boarding")
class BinaryBoardingTest {

    private val example = """
        FBFBBFFRLR
        BFFFBBFRRR
        FFFBBBFRRR
        BBFFBBFRLL
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = BinaryBoarding(example).part1()
            assertEquals(820, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = BinaryBoarding(readBoardingPasses()).part1()
            assertEquals(976, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            val answer = BinaryBoarding(readBoardingPasses()).part2()
            assertEquals(685, answer)
        }
    }

    private fun readBoardingPasses() = readInputAsLines("day05/input.txt")
}
