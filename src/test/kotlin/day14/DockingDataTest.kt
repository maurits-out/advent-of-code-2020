package day14

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 14: Docking Data")
class DockingDataTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val example = """
                    mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                    mem[8] = 11
                    mem[7] = 101
                    mem[8] = 0
                """.trimIndent().lines()
            val answer = DockingData(example).part1()
            assertEquals(165, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = DockingData(readProgram()).part1()
            assertEquals(12610010960049, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val example = """
                mask = 000000000000000000000000000000X1001X
                mem[42] = 100
                mask = 00000000000000000000000000000000X0XX
                mem[26] = 1
            """.trimIndent().lines()
            val answer = DockingData(example).part2()
            assertEquals(208, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = DockingData(readProgram()).part2()
            assertEquals(3608464522781, answer)
        }
    }

    private fun readProgram() = readInputAsLines("day14/input.txt")
}