package day19

class MonsterMessages(rulesInput: List<String>, private val messages: List<String>) {

    private val messagesOfRule42: Set<String>
    private val messagesOfRule31: Set<String>

    init {
        val rules = parseRules(rulesInput)
        messagesOfRule42 = expand(listOf(42), rules).toSet()
        messagesOfRule31 = expand(listOf(31), rules).toSet()
    }

    fun part1(): Int = messages.count { isValidMessagePart1(it) }

    fun part2(): Int = messages.count { isValidMessagePart2(it) }

    private fun isValidMessagePart1(message: String): Boolean {
        if (message.length % 8 != 0) {
            return false
        }
        val chunks = message.chunked(8)
        return chunks.size == 3 && chunks[0] in messagesOfRule42 && chunks[1] in messagesOfRule42 && chunks[2] in messagesOfRule31
    }

    private fun isValidMessagePart2(message: String): Boolean {
        if (message.length % 8 != 0) {
            return false
        }
        val chunks = message.chunked(8)
        var index = 0

        var count42 = 0
        while (index < chunks.size && chunks[index] in messagesOfRule42) {
            count42++
            index++
        }

        var count31 = 0
        while (index < chunks.size && chunks[index] in messagesOfRule31) {
            count31++
            index++
        }
        return index == chunks.size && count31 > 0 && count42 > 0 && count31 < count42
    }

    private fun parseRules(rulesInput: List<String>) = rulesInput.map { rule ->
        val (left, right) = rule.split(": ")
        left.toInt() to right
    }.toMap()

    private fun expand(l: List<Int>, rules: Map<Int, String>): List<String> {
        if (l.all { it in listOf(71, 72) }) {
            return listOf(l.joinToString(separator = "") { if (it == 71) "a" else "b" })
        }
        val index = l.indexOfFirst { it !in listOf(71, 72) }
        return rules
            .getValue(l[index])
            .split(" | ")
            .map { s -> s.split(' ').map { it.toInt() } }
            .map { listOf(l.subList(0, index), it, l.subList(index + 1, l.size)).flatten() }
            .flatMap { expand(it, rules) }
    }
}
