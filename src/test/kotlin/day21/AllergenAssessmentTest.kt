package day21

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 21: Allergen Assessment")
class AllergenAssessmentTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            val answer = AllergenAssessment(readFoodList()).part1()
            assertEquals(2423, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            val answer = AllergenAssessment(readFoodList()).part2()
            assertEquals("jzzjz,bxkrd,pllzxb,gjddl,xfqnss,dzkb,vspv,dxvsp", answer)
        }
    }

    private fun readFoodList() = readInputAsLines("day21/input.txt")
}