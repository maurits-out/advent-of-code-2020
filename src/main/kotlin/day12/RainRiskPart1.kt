package day12

import day12.RainRiskPart1.Direction.EAST

class RainRiskPart1(private val navigationInstructions: List<String>) {

    enum class Direction(val dn: Int, val de: Int) {
        EAST(0, 1), SOUTH(-1, 0), WEST(0, -1), NORTH(1, 0);

        fun turnRight(times: Int): Direction {
            val idx = (this.ordinal + times) % values().size
            return values()[idx]
        }
    }

    fun applyInstructions(): Int =
        navigationInstructions.fold(State(), State::apply).location.manhattanDistance()

    class State(val location: Location = Location(0, 0), private val direction: Direction = EAST) {

        fun apply(instruction: String): State {
            val action = instruction[0]
            val value = instruction.substring(1).toInt()
            return when (action) {
                'F' -> forward(value)
                'L', 'R' -> turn(action, value)
                else -> move(action, value)
            }
        }

        private fun move(m: Char, value: Int): State {
            val newLocation = when (m) {
                'N' -> location.copy(north = location.north + value)
                'S' -> location.copy(north = location.north - value)
                'W' -> location.copy(east = location.east - value)
                else -> location.copy(east = location.east + value)
            }
            return State(newLocation, direction)
        }

        private fun turn(t: Char, value: Int): State =
            if (t == 'R') {
                State(location, direction.turnRight(value / 90))
            } else {
                State(location, direction.turnRight(4 - (value / 90)))
            }

        private fun forward(value: Int): State {
            val (north, east) = location
            val newLocation = Location(north + value * direction.dn, east + value * direction.de)
            return State(newLocation, direction)
        }
    }
}
