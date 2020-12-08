package day07

class HandyHaversacks(private val input: List<String>) {

    private val rules = parseRules()

    fun part1(): Int = findContainingBags("shiny gold").size

    fun part2(): Int = countRequired("shiny gold")

    private fun findContainingBags(bagColor: String): Set<String> {
        val values = rules
            .filterValues { bags -> bags.map { it.first }.contains(bagColor) }
            .keys
        return values.fold(values, { acc, color -> acc.union(findContainingBags(color)) })
    }

    private fun countRequired(bagColor: String): Int =
        rules.getOrDefault(bagColor, emptyList())
            .sumBy { (other, count) -> count + (count * countRequired(other)) }

    private fun parseRules(): Map<String, List<Pair<String, Int>>> {
        return input.map { rule ->
            val groupValues = Regex("(.+) bags contain (.+).").matchEntire(rule)?.groupValues
            val bag = groupValues?.get(1)!!
            val contents = parseContents(groupValues[2])
            bag to contents
        }.toMap()
    }

    private fun parseContents(content: String): List<Pair<String, Int>> =
        if (content == "no other bags")
            emptyList()
        else
            content.split(", ").map {
                val groupValues = Regex("(\\d+) (.+) bags?").matchEntire(it)?.groupValues
                groupValues?.get(2)!! to groupValues[1].toInt()
            }
}
