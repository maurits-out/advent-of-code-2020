package day15

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 15: Rambunctious Recitation")
class RambunctiousRecitationTest {

    private val example = listOf(0, 3, 6)

    private val input = listOf(0,1,5,10,3,12,19)

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = RambunctiousRecitation(example).part1()
            assertEquals(436, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = RambunctiousRecitation(input).part1()
            assertEquals(1373, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = RambunctiousRecitation(example).part2()
            assertEquals(175594, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = RambunctiousRecitation(input).part2()
            assertEquals(112458, answer)
        }
    }
}