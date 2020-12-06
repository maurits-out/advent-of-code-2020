package day06

class CustomCustoms(private val answers: String) {

    fun part1(): Int {
        return answers
            .split("\n\n")
            .map { it.replace("\n", "") }
            .map { it.toSet().size }
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
        val uniqueQuestions = group.flatMap { it.toList() }.toSet()
        return uniqueQuestions.count { question -> group.all { it.contains(question) } }
    }
}
