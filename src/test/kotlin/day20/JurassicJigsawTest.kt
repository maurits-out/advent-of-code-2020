package day20

import common.readInput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 21: Allergen Assessment")
class JurassicJigsawTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            val answer = JurassicJigsaw(readTiles()).part1()
            assertEquals(27798062994017, answer)
        }
    }

    private fun readTiles() = readInput("day20/input.txt")
}