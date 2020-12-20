package day19

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 19: Monster Messages")
class MonsterMessagesTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            val answer = MonsterMessages(readRules(), readMessages()).part1()
            assertEquals(124, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            val answer = MonsterMessages(readRules(), readMessages()).part2()
            assertEquals(228, answer)
        }
    }

    private fun readRules() = readInputAsLines("day19/rules.txt")

    private fun readMessages() = readInputAsLines("day19/messages.txt")
}