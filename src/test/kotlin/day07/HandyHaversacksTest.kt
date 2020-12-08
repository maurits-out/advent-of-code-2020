package day07

import common.readInputAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 7: Handy Haversacks")
class HandyHaversacksTest {

    private val example = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = HandyHaversacks(example).part1()
            assertEquals(4, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = HandyHaversacks(readRules()).part1()
            assertEquals(103, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = HandyHaversacks(example).part2()
            assertEquals(32, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = HandyHaversacks(readRules()).part2()
            assertEquals(1469, answer)
        }
    }

    private fun readRules() = readInputAsLines("day07/input.txt")
}