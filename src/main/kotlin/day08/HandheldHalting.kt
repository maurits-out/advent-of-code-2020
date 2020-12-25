package day08

typealias Instruction = Pair<String, Int>

class HandheldHalting(private val program: String) {

    class InfiniteLoopException(val acc: Int) : Exception()

    private val instructions = parseProgram()

    fun part1(): Int {
        try {
            executeProgram(0, 0, HashSet())
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
                return executeProgram(0, 0, HashSet())
            } catch (e: InfiniteLoopException) {
                swap(pc)
            }
        }
        error("no value returned when swapping any jmp / nop")
    }

    private fun swap(pc: Int) {
        val (operator, value) = instructions[pc]
        if (operator == "nop") {
            instructions[pc] = "jmp" to value
        } else {
            instructions[pc] = "nop" to value
        }
    }

    private tailrec fun executeProgram(pc: Int, acc: Int, executed: MutableSet<Int>): Int {
        if (pc == instructions.size) {
            return acc
        }
        if (pc in executed) {
            throw InfiniteLoopException(acc)
        }
        executed.add(pc)
        val (instruction, value) = instructions[pc]
        return when (instruction) {
            "acc" -> executeProgram(pc + 1, acc + value, executed)
            "jmp" -> executeProgram(pc + value, acc, executed)
            "nop" -> executeProgram(pc + 1, acc, executed)
            else -> error("$instruction not recognized")
        }
    }

    private fun parseProgram(): Array<Instruction> =
        program.lines().map { parseLine(it) }.toTypedArray()

    private fun parseLine(line: String): Instruction {
        val parts = line.split(' ')
        return parts[0] to parts[1].toInt()
    }
}
