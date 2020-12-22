package day22

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 22: Crab Combat")
class CrabCombatTest {

    private val actualStartingDeckPlayer1 = """
        14
        6
        21
        10
        1
        33
        7
        13
        25
        8
        17
        11
        28
        27
        50
        2
        35
        49
        19
        46
        3
        38
        23
        5
        43
    """.trimIndent().lines().map { it.toInt() }

    private val actualStartingDeckPlayer2 = """
        18
        9
        12
        39
        48
        24
        32
        45
        47
        41
        40
        15
        22
        36
        30
        26
        42
        34
        20
        16
        4
        31
        37
        44
        29
    """.trimIndent().lines().map { it.toInt() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = CrabCombat(listOf(9, 2, 6, 3, 1), listOf(5, 8, 4, 7, 10)).part1()
            assertEquals(306, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = CrabCombat(actualStartingDeckPlayer1, actualStartingDeckPlayer2).part1()
            assertEquals(32179, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = CrabCombat(listOf(9, 2, 6, 3, 1), listOf(5, 8, 4, 7, 10)).part2()
            assertEquals(291, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = CrabCombat(actualStartingDeckPlayer1, actualStartingDeckPlayer2).part2()
            assertEquals(30498, answer)
        }
    }
}