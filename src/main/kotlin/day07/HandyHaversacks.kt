package day07

typealias BagColorCount = Pair<String, Int>

class HandyHaversacks(private val input: List<String>) {

    private val ruleRegex = Regex("(.+) bags contain (.+).")
    private val contentRegex = Regex("(\\d+) (.+) bags?")
    private val rules = parseRules()

    fun part1(): Int = findContainingBags("shiny gold").size

    fun part2(): Int = countRequired("shiny gold")

    private fun findContainingBags(bagColor: String): Set<String> {
        val values = rules
            .filterValues { bags -> bags.map { it.first }.contains(bagColor) }
            .keys
        return values.fold(values) { acc, color -> acc.union(findContainingBags(color)) }
    }

    private fun countRequired(bagColor: String): Int =
        rules.getOrDefault(bagColor, emptyList())
            .sumBy { (other, count) -> count + (count * countRequired(other)) }

    private fun parseRules(): Map<String, List<BagColorCount>> {
        return input.map { rule ->
            val (bag, contents) = ruleRegex.matchEntire(rule)!!.destructured
            bag to parseContents(contents)
        }.toMap()
    }

    private fun parseContents(content: String) =
        if (content == "no other bags") {
            emptyList()
        } else {
            content.split(", ").map {
                val (count, color) = contentRegex.matchEntire(it)!!.destructured
                color to count.toInt()
            }
        }
}
