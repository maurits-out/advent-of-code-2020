package day09

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 9: Encoding Error")
class EncodingErrorTest {

    private val example = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent().lines().map { it.toLong() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = EncodingError(example, 5).part1()
            assertEquals(127, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = EncodingError(readSequence(), 25).part1()
            assertEquals(1038347917, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = EncodingError(example, 5).part2()
            assertEquals(62, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = EncodingError(readSequence(), 25).part2()
            assertEquals(137394018, answer)
        }
    }

    private fun readSequence() = readInputAsLines("day09/input.txt").map { it.toLong() }
}