package day05

class BinaryBoarding(private val boardingPasses: List<String>) {

    fun part1(): Int = boardingPasses.maxOf { seatID(it) }

    fun part2(): Int {
        val taken = boardingPasses.map { seatID(it) }.toSet()
        val all = IntRange(taken.minOrNull()!!, taken.maxOrNull()!!)
        return all.minus(taken).first()
    }

    private fun seatID(boardingPass: String): Int {
        val row = location(boardingPass.dropLast(3), 'F', 0 to 127)
        val column = location(boardingPass.takeLast(3), 'L', 0 to 7)
        return row * 8 + column
    }

    private fun location(instructions: String, lowerChar: Char, initial: Pair<Int, Int>): Int =
        instructions.fold(initial, { (first, last), char ->
            val half = (last - first + 1) / 2
            when (char) {
                lowerChar -> first to first + half - 1
                else -> first + half to last
            }
        }).first
}
