package day08

import java.lang.IllegalStateException

class HandheldHalting(private val program: String) {

    private val instructions = parseProgram()

    fun part1(): Int {
        try {
            executeProgram(0, 0, emptySet())
        } catch (e: InfiniteLoopException) {
            return e.acc
        }
        throw IllegalStateException()
    }

    fun part2(): Int {
        val indices = instructions.indices.filter { instructions[it].first != "acc" }
        for (pc in indices) {
            swap(pc)
            try {
                return executeProgram(0, 0, emptySet())
            } catch (e: InfiniteLoopException) {
                swap(pc)
            }
        }
        throw IllegalStateException()
    }

    private fun swap(pc: Int) {
        val (operator, value) = instructions[pc]
        if (operator == "nop") {
            instructions[pc] = "jmp" to value
        } else {
            instructions[pc] = "nop" to value
        }
    }

    private tailrec fun executeProgram(pc: Int, acc: Int, executed: Set<Int>): Int {
        if (pc == instructions.size) {
            return acc
        }
        if (pc in executed) {
            throw InfiniteLoopException(acc)
        }
        val (instruction, value) = instructions[pc]
        return when (instruction) {
            "acc" -> executeProgram(pc + 1, acc + value, executed.union(setOf(pc)))
            "jmp" -> executeProgram(pc + value, acc, executed.union(setOf(pc)))
            else -> executeProgram(pc + 1, acc, executed.union(setOf(pc)))
        }
    }

    private fun parseProgram(): Array<Pair<String, Int>> =
        program.lines().map { parseLine(it) }.toTypedArray()

    private fun parseLine(line: String): Pair<String, Int> {
        val parts = line.split(' ')
        return parts[0] to parts[1].toInt()
    }
}

class InfiniteLoopException(val acc: Int) : Exception()
