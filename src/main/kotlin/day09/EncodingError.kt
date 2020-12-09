package day09

class EncodingError(private val sequence: List<Long>, private val preambleLength: Int) {

    fun part1(): Long {
        return sequence.windowed(preambleLength)
            .zip(sequence.drop(preambleLength))
            .filterNot { (window, sum) -> isValid(window, sum) }
            .map { it.second }
            .first()
    }

    private fun isValid(window: List<Long>, sum: Long): Boolean {
        val combinations = window.flatMap { x ->
            window.map { y ->
                x to y
            }
        }.filter { (x, y) -> x != y }
        return combinations.any { (x, y) -> x + y == sum }
    }

    fun part2(): Long {
        val invalidNumber = part1()
        val set = findContiguousSet(invalidNumber)
        return set.minOrNull()!! + set.maxOrNull()!!
    }

    private fun findContiguousSet(invalidNumber: Long): List<Long> {
        val (start, end) = (0 until sequence.size - 1).flatMap { i ->
            (i + 1 until sequence.size).map { j ->
                i to j
            }
        }.first { (i, j) -> sumSequence(i, j) == invalidNumber }
        return sequence.subList(start, end)
    }

    private fun sumSequence(i: Int, j: Int): Long {
        var sum = 0L
        for (k in i until j) {
            sum += sequence[k]
        }
        return sum
    }
}
