package day03

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 3: Toboggan Trajectory")
class TobogganTrajectoryTest {

    private val example = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent().lines().map { it.toCharArray() }.toTypedArray()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = TobogganTrajectory(example).part1()
            assertEquals(7, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = TobogganTrajectory(readEntries()).part1()
            assertEquals(268, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = TobogganTrajectory(example).part2()
            assertEquals(336, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = TobogganTrajectory(readEntries()).part2()
            assertEquals(3093068400, answer)
        }
    }

    private fun readEntries() = readInputAsLines("day03/input.txt")
        .map { it.toCharArray() }
        .toTypedArray()
}