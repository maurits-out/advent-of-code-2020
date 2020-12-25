package day03

class TobogganTrajectory(private val map: Array<CharArray>) {

    private val height = map.size
    private val width = map[0].size

    fun part1(): Int = countTrees(3, 1)

    fun part2(): Long = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
        .map { (right, down) -> countTrees(right, down) }
        .fold(1) { acc, count -> acc * count }

    private fun countTrees(right: Int, down: Int): Int {

        tailrec fun countTrees(row: Int, column: Int, count: Int): Int {
            if (row >= height) {
                return count
            }
            val delta = if (map[row][column] == '#') 1 else 0
            val nextColumn = column + right
            val correction = if (nextColumn >= width) width else 0
            return countTrees(row + down, nextColumn - correction, count + delta)
        }

        return countTrees(0, 0, 0)
    }
}
