package day16

import common.readInput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

@DisplayName("Day 16: Ticket Translation")
class TicketTranslationTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val example = """
                class: 1-3 or 5-7
                row: 6-11 or 33-44
                seat: 13-40 or 45-50
        
                your ticket:
                7,1,14
        
                nearby tickets:
                7,3,47
                40,4,50
                55,2,20
                38,6,12
            """.trimIndent()
            val answer = TicketTranslation(example).part1()
            assertEquals(71, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = TicketTranslation(readInput()).part1()
            assertEquals(32842, answer)
        }
    }

    private fun readInput() = readInput("day16/input.txt")
}