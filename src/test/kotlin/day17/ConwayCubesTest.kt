package day17

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 17: Conway Cubes")
class ConwayCubesTest {

    private val example = """
        .#.
        ..#
        ###
    """.trimIndent()

    private val input = """
        ....###.
        #...####
        ##.#.###
        ..#.#...
        ##.#.#.#
        #.######
        ..#..#.#
        ######.#
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = Part1(example).run()
            assertEquals(112, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = Part1(input).run()
            assertEquals(333, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = Part2(example).run()
            assertEquals(848, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = Part2(input).run()
            assertEquals(2676, answer)
        }
    }
}