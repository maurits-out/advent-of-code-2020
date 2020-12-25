package day25

class ComboBreaker(private val cardPublicKey: Long, private val doorPublicKey: Long) {

    fun part1(): Pair<Long, Long> {
        val cardLoopSize = calculateLoopSize(cardPublicKey)
        val doorLoopSize = calculateLoopSize(doorPublicKey)
        return transform(cardPublicKey, doorLoopSize) to transform(doorPublicKey, cardLoopSize)
    }

    private fun calculateLoopSize(publicKey: Long): Int {

        tailrec fun calculateLoopSize(loopSize: Int, value: Long): Int =
            if (value == publicKey) {
                loopSize
            } else {
                calculateLoopSize(loopSize + 1, nextValue(value, 7))
            }

        return calculateLoopSize(0, 1)
    }

    private fun transform(subjectNumber: Long, loopSize: Int): Long =
        (1..loopSize).fold(1) { value, _ -> nextValue(value, subjectNumber) }

    private fun nextValue(value: Long, subjectNumber: Long) = (value * subjectNumber) % 20201227
}
