package day12

class RainRiskPart2(private val navigationInstructions: List<String>) {

    fun applyInstructions(): Int =
        navigationInstructions.fold(State(), State::apply).location.manhattanDistance()

    class State(val location: Location = Location(0, 0), private val waypoint: Location = Location(1, 10)) {

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
            val newWaypoint = when (m) {
                'N' -> waypoint.copy(north = waypoint.north + value)
                'S' -> waypoint.copy(north = waypoint.north - value)
                'W' -> waypoint.copy(east = waypoint.east - value)
                else -> waypoint.copy(east = waypoint.east + value)
            }
            return State(location, newWaypoint)
        }

        private fun turn(t: Char, value: Int): State {
            val times = when (t) {
                'R' -> value / 90
                else -> 4 - (value / 90)
            }
            val newWaypoint = (1..times).fold(waypoint) { (n, e), _ -> Location(-e, n) }
            return State(location, newWaypoint)
        }

        private fun forward(value: Int): State {
            val (north, east) = location
            val newLocation = Location(north + value * waypoint.north, east + value * waypoint.east)
            return State(newLocation, waypoint)
        }
    }
}
