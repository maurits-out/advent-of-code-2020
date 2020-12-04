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
        Pair("byr", { isValidYear(it, 1920, 2020) }),
        Pair("iyr", { isValidYear(it, 2010, 2020) }),
        Pair("eyr", { isValidYear(it, 2020, 2030) }),
        Pair("hgt", { it.matches(heightRegex) && (isValidHeightInCm(it) || isValidHeightInIn(it)) }),
        Pair("hcl", { it.matches(hairColorRegex) }),
        Pair("ecl", { eyeColors.contains(it) }),
        Pair("pid", { it.matches(passportIdRegEx) })
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

    private fun parsePassports(): Set<Passport> {
        val passports = hashSetOf<Passport>()
        val currentPassport = hashSetOf<Pair<String, String>>()
        for (line in input.lines()) {
            if (line.isEmpty()) {
                passports.add(currentPassport.toMap())
                currentPassport.clear()
            } else {
                val fields = extractFields(line)
                currentPassport.addAll(fields)
            }
        }
        return passports
    }

    private fun extractFields(line: String): Set<Pair<String, String>> {
        val fields = hashSetOf<Pair<String, String>>()
        val parts = line.split(':', ' ')
        for (i in parts.indices step 2) {
            fields.add(Pair(parts[i], parts[i + 1]))
        }
        return fields
    }
}
