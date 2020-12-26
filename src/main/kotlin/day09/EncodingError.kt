package day09

class EncodingError(private val sequence: List<Long>, private val preambleLength: Int) {

    fun part1() = sequence.windowed(preambleLength)
        .zip(sequence.drop(preambleLength))
        .filterNot { (window, sum) -> isValid(window, sum) }
        .map { it.second }
        .first()

    private fun isValid(window: List<Long>, sum: Long) =
        window.flatMap { x ->
            window.map { y ->
                x to y
            }
        }.filter { (x, y) ->
            x != y
        }.any { (x, y) ->
            x + y == sum
        }

    fun part2(): Long {
        val set = findContiguousSet(part1())
        return set.minOrNull()!! + set.maxOrNull()!!
    }

    private fun findContiguousSet(invalidNumber: Long): List<Long> =
        (0 until sequence.size - 1).asSequence().flatMap { i ->
            (i + 1 until sequence.size).map { j ->
                sequence.subList(i, j)
            }
        }.first { it.sum() == invalidNumber }
}
