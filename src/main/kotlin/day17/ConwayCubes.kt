package day17

abstract class ConwayCubes(private val input: String) {

    data class Cubic(val x: Int, val y: Int, val z: Int, val w: Int)

    fun run(): Int = IntRange(1, 6)
        .fold(initialState(input)) { state, _ -> nextState(state) }
        .size

    private fun initialState(input: String): Set<Cubic> =
        input.lines().flatMapIndexed { y: Int, row: String ->
            row.mapIndexedNotNull { x: Int, column: Char ->
                if (column == '#') {
                    Cubic(x, y, 0, 0)
                } else {
                    null
                }
            }
        }.toSet()

    abstract fun neighbors(cubic: Cubic): Set<Cubic>

    private fun countActiveNeighbors(state: Set<Cubic>, cubic: Cubic): Int =
        neighbors(cubic).filter { it in state }.size

    private fun nextState(state: Set<Cubic>): Set<Cubic> {
        val activeToInactive = state
            .filter { countActiveNeighbors(state, it) !in 2..3 }
            .toSet()
        val inactiveToActive = state
            .flatMap { neighbors(it) }
            .filter { countActiveNeighbors(state, it) == 3 }
            .toSet()
        return state.minus(activeToInactive).union(inactiveToActive)
    }
}

class Part1(input: String) : ConwayCubes(input) {

    override fun neighbors(cubic: Cubic): Set<Cubic> {
        val (x0, y0, z0, _) = cubic
        return IntRange(x0 - 1, x0 + 1).flatMap { x ->
            IntRange(y0 - 1, y0 + 1).flatMap { y ->
                IntRange(z0 - 1, z0 + 1).map { z ->
                    Cubic(x, y, z, 0)
                }
            }
        }.filter { it != cubic }.toSet()
    }
}

class Part2(input: String): ConwayCubes(input) {

    override fun neighbors(cubic: Cubic): Set<Cubic> {
        val (x0, y0, z0, w0) = cubic
        return IntRange(x0 - 1, x0 + 1).flatMap { x ->
            IntRange(y0 - 1, y0 + 1).flatMap { y ->
                IntRange(z0 - 1, z0 + 1).flatMap { z ->
                    IntRange(w0 - 1, w0 + 1).map { w ->
                        Cubic(x, y, z, w)
                    }
                }
            }
        }.filter { it != cubic }.toSet()
    }
}
