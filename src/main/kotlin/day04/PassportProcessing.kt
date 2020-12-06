package day04

typealias Passport = Map<String, String>

class PassportProcessing(private val input: String) {

    fun part1(): Int = parsePassports().count { containsRequiredFields(it) }

    fun part2(): Int = parsePassports().count { containsRequiredFields(it) && containsValidFields(it) }

    private val requiredFields = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    private val yearRegex = "\\d{4}".toRegex()
    private val heightRegex = "\\d+(?:cm|in)".toRegex()
    private val hairColorRegex = "#[0-9a-f]{6}".toRegex()
    private val eyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    private val passportIdRegEx = "\\d{9}".toRegex()

    private val validators = mapOf<String, (String) -> Boolean>(
        "byr" to { isValidYear(it, 1920, 2020) },
        "iyr" to { isValidYear(it, 2010, 2020) },
        "eyr" to { isValidYear(it, 2020, 2030) },
        "hgt" to { it.matches(heightRegex) && (isValidHeightInCm(it) || isValidHeightInIn(it)) },
        "hcl" to { it.matches(hairColorRegex) },
        "ecl" to { it in eyeColors },
        "pid" to { it.matches(passportIdRegEx) }
    )

    private fun containsRequiredFields(passport: Passport): Boolean =
        passport.keys.containsAll(requiredFields)

    private fun containsValidFields(passport: Passport): Boolean =
        validators
            .filterNot { entry ->
                val field = entry.key
                val validator = entry.value
                validator(passport.getValue(field))
            }
            .isEmpty()

    private fun isValidHeightInIn(value: String): Boolean = isValidHeightRange(value, "in", 59, 76)

    private fun isValidHeightInCm(value: String) = isValidHeightRange(value, "cm", 150, 193)

    private fun isValidHeightRange(value: String, unit: String, min: Int, max: Int) =
        value.endsWith(unit) && value.dropLast(2).toInt() in min..max

    private fun isValidYear(value: String, min: Int, max: Int) =
        value.matches(yearRegex) && value.toInt() in min..max

    private fun parsePassports(): List<Passport> = input.split("\n\n")
            .map { it.replace('\n', ' ') }
            .map { extractFields(it) }

    private fun extractFields(line: String): Map<String, String> = line.split(' ')
        .associate {
            it.split(':').let { (field, value) -> field to value }
        }
}
