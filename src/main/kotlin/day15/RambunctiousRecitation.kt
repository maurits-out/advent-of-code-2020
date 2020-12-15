package day15

class RambunctiousRecitation(private val input: List<Int>) {

    fun part1(): Int {
        return play(2020)
    }

    fun part2(): Int {
        return play(30000000)
    }

    private fun play(turns: Int): Int {
        val memory = HashMap<Int, Int>()
        input.dropLast(1).mapIndexed { index, value -> value to index + 1 }.toMap(memory)
        var lastSpoken = input.last()
        for (turn in (input.size + 1)..turns) {
            val next = nextNumberToSpeak(lastSpoken, memory, turn)
            memory[lastSpoken] = turn - 1
            lastSpoken = next
        }
        return lastSpoken
    }

    private fun nextNumberToSpeak(lastSpoken: Int, memory: Map<Int, Int>, turn: Int) =
        if (lastSpoken in memory) {
            turn - memory.getValue(lastSpoken) - 1
        } else {
            0
        }
}
