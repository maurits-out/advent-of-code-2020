package day10

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 10: Adapter Array")
class AdapterArrayTest {

    private val example = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent().lines().map { it.toInt() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = AdapterArray(example).part1()
            assertEquals(220, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = AdapterArray(readAdapterRatings()).part1()
            assertEquals(2343, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = AdapterArray(example).part2()
            assertEquals(19208, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = AdapterArray(readAdapterRatings()).part2()
            assertEquals(31581162962944, answer)
        }
    }

    private fun readAdapterRatings() = readInputAsLines("day10/input.txt").map { it.toInt() }
}