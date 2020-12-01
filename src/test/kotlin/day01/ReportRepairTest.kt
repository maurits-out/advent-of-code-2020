package day01

import common.readInputAsLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1: Report Repair")
internal class ReportRepairTest {

    private val example = intArrayOf(1721, 979, 366, 299, 675, 1456)

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = ReportRepair(example).part1()
            assertEquals(514579, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = ReportRepair(readEntries()).part1()
            assertEquals(1020036, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = ReportRepair(example).part2()
            assertEquals(241861950, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = ReportRepair(readEntries()).part2()
            assertEquals(286977330, answer)
        }
    }

    private fun readEntries() = readInputAsLines("day01/input.txt")
        .map { it.toInt() }
        .toIntArray()
}
