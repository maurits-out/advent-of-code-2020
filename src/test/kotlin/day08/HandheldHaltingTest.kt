package day08

import common.readInput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 8: Handheld Halting")
class HandheldHaltingTest {

    private val example = """
        nop +0
        acc +1
        jmp +4
        acc +3
        jmp -3
        acc -99
        acc +1
        jmp -4
        acc +6
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = HandheldHalting(example).part1()
            assertEquals(5, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = HandheldHalting(readProgram()).part1()
            assertEquals(1420, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = HandheldHalting(example).part2()
            assertEquals(8, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = HandheldHalting(readProgram()).part2()
            assertEquals(1245, answer)
        }
    }

    private fun readProgram() = readInput("day08/input.txt")
}