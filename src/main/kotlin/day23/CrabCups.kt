package day23

import java.lang.Character.getNumericValue
import java.lang.StringBuilder

class CrabCups(private val input: String) {

    fun part1(): String {
        val circle = play(input.map { getNumericValue(it) }, 100)
        return convertToString(circle)
    }

    fun part2(): Long {
        val cupsFromInput = input.map { getNumericValue(it) }
        val additionalCups = IntRange(cupsFromInput.maxOrNull()!! + 1, 1000000)
        val circle = play(listOf(cupsFromInput, additionalCups).flatten(), 10000000)
        return multiplyLabelsAfterCup1(circle)
    }

    private fun play(cups: List<Int>, totalMoves: Int): Map<Int, Int> {
        val circle = cupsToCircle(cups)
        var current = cups.first()
        for (move in 1..totalMoves) {
            val (next1, next2, next3) = selectNextThreeCupsToPickUp(current, circle)
            val destination = findDestination(current, next1, next2, next3, cups.size)
            moveToDestination(circle, current, next1, next3, destination)
            current = circle.getValue(current)
        }
        return circle
    }

    private fun moveToDestination(circle: MutableMap<Int, Int>, current: Int, next1: Int, next3: Int, destination: Int) {
        circle[current] = circle.getValue(next3)
        circle[next3] = circle.getValue(destination)
        circle[destination] = next1
    }

    private fun selectNextThreeCupsToPickUp(current: Int, circle: Map<Int, Int>): Triple<Int, Int, Int> {
        val next1 = circle.getValue(current)
        val next2 = circle.getValue(next1)
        val next3 = circle.getValue(next2)
        return Triple(next1, next2, next3)
    }

    private fun convertToString(circle: Map<Int, Int>): String {
        val result = StringBuilder()
        var cup = circle.getValue(1)
        while (cup != 1) {
            result.append(cup)
            cup = circle.getValue(cup)
        }
        return result.toString()
    }

    private fun multiplyLabelsAfterCup1(circle: Map<Int, Int>): Long {
        val labelOfCupAfter1 = circle.getValue(1)
        return labelOfCupAfter1.toLong() * circle.getValue(labelOfCupAfter1)
    }

    private fun findDestination(current: Int, next1: Int, next2: Int, next3: Int, max: Int): Int {
        var candidate = current - 1
        val exclude = setOf(next1, next2, next3)
        while (candidate > 0 && candidate in exclude) {
            candidate--
        }
        if (candidate == 0) {
            candidate = max
            while (candidate in exclude) {
                candidate--
            }
        }
        return candidate
    }

    private fun cupsToCircle(cups: List<Int>): MutableMap<Int, Int> {
        val circle = hashMapOf<Int, Int>()
        cups.zipWithNext().toMap(circle)
        circle[cups.last()] = cups.first()
        return circle
    }
}
