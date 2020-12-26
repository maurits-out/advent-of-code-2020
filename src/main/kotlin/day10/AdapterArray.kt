package day10

class AdapterArray(adapters: List<Int>) {

    private val sortedAdapters = adapters.sorted().toIntArray()

    fun part1(): Int {

        tailrec fun part1(i: Int, previousRating: Int, num1JoltDifferences: Int, num3JoltDifferences: Int): Int {
            if (i == sortedAdapters.size) {
                return num1JoltDifferences * (num3JoltDifferences + 1)
            }
            val rating = sortedAdapters[i]
            return if (rating - previousRating == 1) {
                part1(i + 1, rating, num1JoltDifferences + 1, num3JoltDifferences)
            } else {
                part1(i + 1, rating, num1JoltDifferences, num3JoltDifferences + 1)
            }
        }

        return part1(0, 0, 0, 0)
    }

    fun part2(): Long {
        val table = LongArray(sortedAdapters.maxOrNull()!! + 1)
        table[0] = 1
        for (rating in sortedAdapters) {
            table[rating] = listOf(rating - 1, rating - 2, rating - 3).filter { it >= 0 }.sumOf { table[it] }
        }
        return table.last()
    }
}
