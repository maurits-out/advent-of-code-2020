package day18

class OperationOrder(private val input: List<String>) {

    fun part1(): Long = input.map { evaluateSimplifiedPart1(simplifyPart1(it)) }.sum()

    fun part2(): Long = input.map { evaluateSimplifiedPart2(simplifyPart2(it)) }.sum()

    private fun simplifyPart1(expression: String): String {
        val subResults = subExpressions(expression).map { s ->
            s to evaluateSimplifiedPart1(simplifyPart1(s))
        }.toMap()
        return subResults.keys.fold(expression) { acc, s ->
            acc.replace("($s)", subResults[s].toString())
        }
    }

    private fun simplifyPart2(expression: String): String {
        val subResults = subExpressions(expression).map { s ->
            s to evaluateSimplifiedPart2(simplifyPart2(s))
        }.toMap()
        return subResults.keys.fold(expression) { acc, s ->
            acc.replace("($s)", subResults[s].toString())
        }
    }

    private fun evaluateSimplifiedPart1(expression: String): Long {
        val tokens = expression.split(" ").toTypedArray()
        var index = 1
        var acc = tokens[0].toLong()
        while (index < tokens.size) {
            val argument = tokens[index + 1].toLong()
            if (tokens[index] == "+") {
                acc += argument
            } else {
                acc *= argument
            }
            index += 2
        }
        return acc
    }

    private fun evaluateSimplifiedPart2(expression: String): Long {
        val tokens = expression.split(" ").toTypedArray()
        var index = 1
        val newTokens = mutableListOf<String>()
        var acc = tokens[0].toLong()
        while (index < tokens.size) {
            val argument = tokens[index + 1].toLong()
            if (tokens[index] == "+") {
                acc += argument
            } else {
                newTokens.add(acc.toString())
                newTokens.add("*")
                acc = tokens[index + 1].toLong()
            }
            index += 2
        }
        newTokens.add(acc.toString())
        val newExpression = newTokens.joinToString(separator = " ")
        val result = evaluateSimplifiedPart1(newExpression)
        println("$expression $result")
        return result
    }

    private fun subExpressions(expression: String): List<String> {
        val result = mutableListOf<String>()
        var i = 0
        var start = 0
        var nesting = 0
        while (i < expression.length) {
            if (expression[i] == '(') {
                nesting++
                if (nesting == 1) {
                    start = i + 1
                }
            } else if (expression[i] == ')') {
                nesting--
                if (nesting == 0) {
                    result.add(expression.substring(start, i))
                }
            }
            i++
        }
        return result
    }
}
