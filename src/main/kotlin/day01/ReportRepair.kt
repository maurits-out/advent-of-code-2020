package day01

class ReportRepair(private val entries: IntArray) {

    private val size = entries.size
    private val sum = 2020

    fun part1() =
        (0 until size - 1).asSequence().flatMap { i ->
            (i + 1 until size).map { j -> i to j }
        }.filter { (i, j) ->
            entries[i] + entries[j] == sum
        }.map { (i, j) ->
            entries[i] * entries[j]
        }.first()

    fun part2() =
        (0 until size - 2).asSequence().flatMap { i ->
            (i + 1 until size - 1).flatMap { j ->
                (j + 1 until size).map { k ->
                    Triple(i, j, k)
                }
            }
        }.filter { (i, j, k) ->
            entries[i] + entries[j] + entries[k] == sum
        }.map { (i, j, k) ->
            entries[i] * entries[j] * entries[k]
        }.first()
}