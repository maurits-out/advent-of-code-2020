package day16

class TicketTranslation(input: String) {

    private val ruleRegex = "([ \\w]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)".toRegex()

    private val notes: Notes = parseInput(input)

    data class Rule(val ranges: List<IntRange>)

    data class Ticket(val fields: List<Int>)

    data class Notes(val rules: Map<String, Rule>, val yourTicket: Ticket, val nearbyTickets: List<Ticket>)

    fun part1(): Int =
        notes.nearbyTickets.flatMap { it.fields }.filterNot { isValueValid(it) }.sum()

    fun part2() {
        val validNearTickets = notes.nearbyTickets.filterNot { isInvalid(it) }

        for (index in 0 until notes.rules.size) {
            val candidates = findCandidateFields(index, validNearTickets)
            println("$index: $candidates")
        }
    }

    private fun findCandidateFields(index: Int, validNearTickets: List<Ticket>): Set<String> {
        return notes.rules.toList().filter { (_, rule) -> isCandidate(rule.ranges, index, validNearTickets) }.map { it.first }.toSet()
    }

    private fun isCandidate(ranges: List<IntRange>, index: Int, validNearTickets: List<Ticket>): Boolean {
        return validNearTickets.map { it.fields[index] }.all { isValueValid(it, ranges) }
    }

    private fun isInvalid(ticket: Ticket) = ticket.fields.any { !isValueValid(it) }

    private fun isValueValid(value: Int): Boolean = notes.rules.values.any { isValueValid(value, it.ranges) }

    private fun isValueValid(value: Int, ranges: List<IntRange>): Boolean = ranges.any { value in it }

    private fun parseInput(input: String): Notes {
        val sections = input.split("\n\n").toTypedArray()

        val rules = sections[0]
            .split("\n")
            .map { parseRuleLine(it) }
            .toMap()

        val yourTicket = sections[1]
            .split("\n")
            .drop(1)
            .map { parseTicketLine(it) }
            .single()

        val nearTickets = sections[2]
            .split("\n")
            .drop(1)
            .map { parseTicketLine(it) }
            .toList()

        return Notes(rules, yourTicket, nearTickets)
    }

    private fun parseRuleLine(ruleLine: String): Pair<String, Rule> =
        ruleRegex.matchEntire(ruleLine)
            ?.destructured
            ?.let { (name, min1, max1, min2, max2) ->
                val range1 = IntRange(min1.toInt(), max1.toInt())
                val range2 = IntRange(min2.toInt(), max2.toInt())
                name to Rule(listOf(range1, range2))
            }!!

    private fun parseTicketLine(value: String) = Ticket(value.split(',').map { it.toInt() })
}
