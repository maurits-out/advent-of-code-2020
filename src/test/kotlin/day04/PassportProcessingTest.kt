package day04

import common.readInput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 4: Passport Processing")
class PassportProcessingTest {

    private val example = """
        ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm

        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929

        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm

        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = PassportProcessing(example).part1()
            assertEquals(2, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = PassportProcessing(readInput()).part1()
            assertEquals(190, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = PassportProcessing(example).part2()
            assertEquals(2, answer)
        }

        @Test
        fun `Actual answer`() {
            val answer = PassportProcessing(readInput()).part2()
            assertEquals(121, answer)
        }
    }

    private fun readInput() = readInput("day04/input.txt")
}
