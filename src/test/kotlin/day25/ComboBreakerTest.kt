package day25

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 25: Combo Breaker")
class ComboBreakerTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = ComboBreaker(5764801, 17807724).part1()
            assertEquals(14897079L to 14897079L, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = ComboBreaker(13316116, 13651422).part1()
            assertEquals(12929L to 12929L, answer)
        }
    }
}
