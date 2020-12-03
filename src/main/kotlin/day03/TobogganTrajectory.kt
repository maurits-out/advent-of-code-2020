package day03

class TobogganTrajectory(private val map: Array<CharArray>) {

    private val height = map.size
    private val width = map[0].size

    fun part1(): Long = countTrees(3, 1)

    fun part2(): Long {
        return listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
            .map { (right, down) -> countTrees(right, down) }
            .fold(1, { acc, count -> acc * count })
    }

    private fun countTrees(right: Int, down: Int): Long {
        var row = 0
        var column = 0
        var count = 0L
        while (row < height) {
            if (map[row][column] == '#') {
                count++
            }
            row += down
            column += right
            if (column >= width) {
                column -= width
            }
        }
        return count
    }
}
