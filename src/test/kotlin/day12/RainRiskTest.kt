package day12

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 12: Rain Risk")
class RainRiskTest {

    private val example = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = RainRiskPart1(example).applyInstructions()
            assertEquals(25, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = RainRiskPart1(readNavigationInstructions()).applyInstructions()
            assertEquals(420, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = RainRiskPart2(example).applyInstructions()
            assertEquals(286, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = RainRiskPart2(readNavigationInstructions()).applyInstructions()
            assertEquals(12, answer)
        }
    }

    private fun readNavigationInstructions() = readInputAsLines("day12/input.txt")
}