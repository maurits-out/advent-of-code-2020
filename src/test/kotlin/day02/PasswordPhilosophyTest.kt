package day02

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 2: Password Philosophy")
class PasswordPhilosophyTest {

    private val example = listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = PasswordPhilosophy(example).part1()
            assertEquals(2, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = PasswordPhilosophy(readEntries()).part1()
            assertEquals(600, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = PasswordPhilosophy(example).part2()
            assertEquals(1, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = PasswordPhilosophy(readEntries()).part2()
            assertEquals(245, answer)
        }
    }

    private fun readEntries() = readInputAsLines("day02/input.txt")
}