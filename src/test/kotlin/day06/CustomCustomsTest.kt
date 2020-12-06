package day06

import common.readInput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

@DisplayName("Day 6: Custom Customs")
class CustomCustomsTest {

    private val example = """
        abc

        a
        b
        c

        ab
        ac

        a
        a
        a
        a

        b
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = CustomCustoms(example).part1()
            assertEquals(11, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = CustomCustoms(readAnswers()).part1()
            assertEquals(6506, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = CustomCustoms(example).part2()
            assertEquals(6, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = CustomCustoms(readAnswers()).part2()
            assertEquals(3243, answer)
        }
    }

    private fun readAnswers() = readInput("day06/input.txt")
}
