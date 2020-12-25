package day06

class CustomCustoms(private val answers: String) {

    fun part1(): Int {
        return answers
            .split("\n\n")
            .map { it.replace("\n", "").toSet().size }
            .sum()
    }

    fun part2(): Int {
        return answers
            .split("\n\n")
            .map { it.split("\n") }
            .map { countQuestionsAnsweredByAll(it) }
            .sum()
    }

    private fun countQuestionsAnsweredByAll(group: List<String>): Int {
        return group
            .joinToString("")
            .groupBy { it }
            .count { it.value.size == group.size }
    }
}
