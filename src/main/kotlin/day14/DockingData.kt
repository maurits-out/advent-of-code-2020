package day14

class DockingData(private val program: List<String>) {

    private val assignmentRegex = Regex("mem\\[(\\d+)] = (\\d+)")

    data class State(val memory: Map<Long, Long> = hashMapOf(), val mask: String? = null)

    fun part1(): Long {
        val endState = program.fold(State()) { state, instruction -> applyPart1(state, instruction) }
        return endState.memory.values.sum()
    }

    fun part2(): Long {
        val endState = program.fold(State()) { state, instruction -> applyPart2(state, instruction) }
        return endState.memory.values.sum()
    }

    private fun applyPart1(state: State, instruction: String): State {
        if (instruction.startsWith("mask = ")) {
            return state.copy(mask = instruction.takeLast(36))
        }
        return assignmentRegex.matchEntire(instruction)
            ?.destructured
            ?.let { (address, value) ->
                val maskedValue = applyMaskToValue(value.toLong(), state.mask!!)
                state.copy(memory = updateMemory(address.toLong(), maskedValue, state.memory))
            }!!
    }

    private fun applyMaskToValue(value: Long, mask: String): Long {
        return convertToBinaryString(value)
            .zip(mask)
            .map { (v, m) -> if (m == 'X') v else m }
            .joinToString("")
            .toLong(2)
    }

    private fun applyPart2(state: State, instruction: String): State {
        if (instruction.startsWith("mask = ")) {
            return state.copy(mask = instruction.takeLast(36))
        }
        return assignmentRegex.matchEntire(instruction)
            ?.destructured
            ?.let { (address, value) ->
                val addresses = applyMaskToAddress(address.toLong(), state.mask!!)
                state.copy(memory = updateMemory(addresses, value.toLong(), state.memory))
            }!!
    }

    private fun updateMemory(address: Long, value: Long, memory: Map<Long, Long>): Map<Long, Long> {
        val mutableMemory = memory.toMutableMap()
        mutableMemory[address] = value
        return mutableMemory.toMap()
    }

    private fun updateMemory(addresses: Set<Long>, value: Long, memory: Map<Long, Long>): Map<Long, Long> {
        val mutableMemory = memory.toMutableMap()
        addresses.forEach {
            mutableMemory[it] = value
        }
        return mutableMemory.toMap()
    }

    private fun applyMaskToAddress(address: Long, mask: String): Set<Long> {
        val template = convertToBinaryString(address).zip(mask).map { (v, m) ->
            when (m) {
                '0' -> v
                '1' -> '1'
                else -> 'X'
            }
        }.joinToString("")
        return expand(template)
    }

    private fun expand(template: String): Set<Long> =
        when (template.contains('X')) {
            false ->
                setOf(template.toLong(2))
            else -> {
                val withZero = expand(template.replaceFirst('X', '0'))
                val withOne = expand(template.replaceFirst('X', '1'))
                withZero.union(withOne)
            }
        }

    private fun convertToBinaryString(value: Long) =
        String.format("%36s", value.toString(2)).replace(' ', '0')
}
