package day13

/**
 * My input (bus ID, offset):
 *
 * 1002632
 * (23, 0), (41, 13), (829, 23), (13, 36), (17, 37), (29, 52), (677, 54), (37, 60), (19, 73)
 *
 * The first part requires no programming, and can be done by hand (and a calculator :) ).
 *
 * Part 2 however.... I tried the following:
 *
 * Take largest bus ID: 829, subtract its offset from all other offsets:
 *
 * (23, -23), (41, -10), (829, 0), (13, 13), (17, 14), (29, 29), (677, 31), (37, 37), (19, 50)
 *
 * Earliest timestamp + 23 must be divisible by 829, 37, 29, 23, 13. A sequence that satisfies this is
 * (829 * 37 * 29 * 23 * 13) * t, with t >= 0.
 *
 * Iterate over this sequence and take the first t0 such that
 * (t0 - 10) % 41 = 0, (t0 + 14) % 17 = 0, (t0 + 31) % 677 = 0, and (t0 + 50) % 19 = 0
 *
 * The answer is then t0 - 23.
 */
fun offsets() = sequence {
    var next = 0L
    while (true) {
        yield(next)
        next += 829L * 37 * 29 * 23 * 13
    }
}

fun main() {
    val answer = offsets()
        .filter { ((it + 31) % 677L == 0L) && ((it - 10) % 41 == 0L) && ((it + 14) % 17 == 0L) && ((it + 50) % 19 == 0L) }
        .first() - 23
    println("Part 2: $answer")
}
