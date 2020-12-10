package day10

class AdapterArray(private val adapters: List<Int>) {

    fun part1(): Int {
        var currentRating = 0
        var num1JoltDifferences = 0
        var num3JoltDifferences = 0
        for (rating in adapters.sorted()) {
            when (rating - currentRating) {
                1 -> num1JoltDifferences++
                3 -> num3JoltDifferences++
                else -> continue
            }
            currentRating = rating
        }
        return num1JoltDifferences * (num3JoltDifferences + 1)
    }

    fun part2(): Long {
        val table = LongArray(adapters.maxOrNull()!! + 1)
        table[0] = 1
        for (rating in adapters.sorted()) {
            table[rating] = listOf(rating - 1, rating - 2, rating - 3).filter { it >= 0 }.map { table[it] }.sum()
        }
        return table.last()
    }
}
