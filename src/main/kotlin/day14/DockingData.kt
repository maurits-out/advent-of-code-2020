package day14

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class DockingData(private val program: List<String>) {

    private val assignmentRegex = Regex("""mem\[(\d+)] = (\d+)""")

    data class State(val memory: MutableMap<Long, Long> = HashMap(), val mask: String? = null)

    fun part1() = program.fold(State()) { state, instruction -> applyPart1(state, instruction) }.memory.values.sum()

    fun part2() = program.fold(State()) { state, instruction -> applyPart2(state, instruction) }.memory.values.sum()

    private fun applyPart1(state: State, instruction: String): State {
        if (instruction.startsWith("mask = ")) {
            return state.copy(mask = instruction.takeLast(36))
        }
        val (address, value) = assignmentRegex.matchEntire(instruction)!!.destructured
        val maskedValue = applyMaskToValue(value.toLong(), state.mask!!)
        state.memory[address.toLong()] = maskedValue
        return state
    }

    private fun applyMaskToValue(value: Long, mask: String): Long =
        convertToBinaryString(value)
            .zip(mask)
            .joinToString("") { (v, m) -> (if (m == 'X') v else m).toString() }
            .toLong(2)

    private fun applyPart2(state: State, instruction: String): State {
        if (instruction.startsWith("mask = ")) {
            return state.copy(mask = instruction.takeLast(36))
        }
        val (address, value) = assignmentRegex.matchEntire(instruction)!!.destructured
        applyMaskToAddress(address.toLong(), state.mask!!).forEach {
            state.memory[it] = value.toLong()
        }
        return state
    }

    private fun applyMaskToAddress(address: Long, mask: String): Set<Long> {
        val template = convertToBinaryString(address)
            .zip(mask)
            .joinToString("") { (v, m) -> (if (m == '0') v else m).toString() }
        return expand(template)
    }

    private fun expand(template: String): Set<Long> {
        val result = HashSet<Long>()
        val templates = LinkedList(listOf(template))
        while (templates.isNotEmpty()) {
            val t = templates.poll()!!
            if (t.contains('X')) {
                templates.push(t.replaceFirst('X', '0'))
                templates.push(t.replaceFirst('X', '1'))
            } else {
                result.add(t.toLong(2))
            }
        }
        return result
    }

    private fun convertToBinaryString(value: Long) =
        String.format("%36s", value.toString(2)).replace(' ', '0')
}
