package day23

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 23: Crab Cups")
class CrabCupsTest {

    private val example = "389125467"
    private val actualInput = "318946572"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = CrabCups(example).part1()
            assertEquals("67384529", answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = CrabCups(actualInput).part1()
            assertEquals("52864379", answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = CrabCups(example).part2()
            assertEquals(149245887792, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = CrabCups(actualInput).part2()
            assertEquals(11591415792, answer)
        }
    }
}