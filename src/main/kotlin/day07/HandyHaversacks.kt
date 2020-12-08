package day07

class HandyHaversacks(private val rules: List<String>) {

    private val ruleMap = parseRules()

    fun part1(): Int = findContainingBags("shiny gold").size

    private fun findContainingBags(bagColor: String): Set<String> {
        val values = ruleMap.getOrDefault(bagColor, emptyList())
        return values.fold(values.toSet(), { acc, color -> acc.union(findContainingBags(color)) })
    }

    private fun parseRules(): Map<String, List<String>> {
        return rules.flatMap { rule ->
            val groupValues = Regex("(.+) bags contain (.+).").matchEntire(rule)?.groupValues
            val bag = groupValues?.get(1)!!
            val contents = parseContents(groupValues[2])
            contents.map { it to bag }
        }.groupBy({ (k, _) -> k }, { (_, v) -> v })
    }

    private fun parseContents(content: String): Set<String> =
        if (content == "no other bags")
            emptySet()
        else
            content.split(", ").map {
                val groupValues = Regex("\\d+ (.+) bags?").matchEntire(it)?.groupValues
                groupValues?.get(1)!!
            }.toSet()

    fun part2(): Int = 0
}


