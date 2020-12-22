package day22

import java.util.*

class CrabCombat(private val startingDeckPlayer1: List<Int>, private val startingDeckPlayer2: List<Int>) {

    fun part1(): Int {
        return calculateScore(play())
    }

    fun part2(): Int {
        val (_, deck) = playRecursive(LinkedList(startingDeckPlayer1), LinkedList(startingDeckPlayer2))
        return calculateScore(deck)
    }

    private fun play(): List<Int> {
        val deckPlayer1 = LinkedList(startingDeckPlayer1)
        val deckPlayer2 = LinkedList(startingDeckPlayer2)
        while (deckPlayer1.isNotEmpty() && deckPlayer2.isNotEmpty()) {
            val cardPlayer1 = deckPlayer1.poll()!!
            val cardPlayer2 = deckPlayer2.poll()!!
            if (cardPlayer1 > cardPlayer2) {
                deckPlayer1.addAll(listOf(cardPlayer1, cardPlayer2))
            } else {
                deckPlayer2.addAll(listOf(cardPlayer2, cardPlayer1))
            }
        }
        return if (deckPlayer1.isNotEmpty()) deckPlayer1 else deckPlayer2
    }

    private fun playRecursive(startingDeckPlayer1: List<Int>, startingDeckPlayer2: List<Int>): Pair<Int, List<Int>> {
        val deckPlayer1: LinkedList<Int> = LinkedList(startingDeckPlayer1)
        val deckPlayer2: LinkedList<Int> = LinkedList(startingDeckPlayer2)
        val previousDecks = hashSetOf<Pair<List<Int>, List<Int>>>()
        while (deckPlayer1.isNotEmpty() && deckPlayer2.isNotEmpty()) {
            if (Pair(deckPlayer1, deckPlayer2) in previousDecks) {
                return 1 to deckPlayer1
            }
            previousDecks.add(deckPlayer1.toList() to deckPlayer2.toList())

            val (cardPlayer1, cardPlayer2) = deckPlayer1.poll()!! to deckPlayer2.poll()!!
            val player1Wins: Boolean = if (deckPlayer1.size >= cardPlayer1 && deckPlayer2.size >= cardPlayer2) {
                playRecursive(deckPlayer1.take(cardPlayer1), deckPlayer2.take(cardPlayer2)).first == 1
            } else {
                cardPlayer1 > cardPlayer2
            }

            if (player1Wins) {
                deckPlayer1.addAll(listOf(cardPlayer1, cardPlayer2))
            } else {
                deckPlayer2.addAll(listOf(cardPlayer2, cardPlayer1))
            }
        }

        return if (deckPlayer1.isNotEmpty()) 1 to deckPlayer1 else 2 to deckPlayer2
    }

    private fun calculateScore(deck: List<Int>): Int {
        return (1..(deck.size)).zip(deck.reversed()).sumBy { (n, m) -> n * m }
    }
}
