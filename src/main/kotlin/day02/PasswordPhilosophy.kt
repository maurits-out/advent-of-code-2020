package day02

class PasswordPhilosophy(private val input: List<String>) {

    fun part1(): Int = input.count { isValidPasswordPart1(it) }

    fun part2(): Int = input.count { isValidPasswordPart2(it) }

    private fun isValidPasswordPart1(line: String): Boolean {
        return isValidPassword(line) { password, char, min, max ->
            password.count { it == char } in min..max
        }
    }

    private fun isValidPasswordPart2(line: String): Boolean {
        return isValidPassword(line) { password, char, pos1, pos2 ->
            (password[pos1 - 1] == char) xor (password[pos2 - 1] == char)
        }
    }

    private fun isValidPassword(line: String, predicate: (CharArray, Char, Int, Int) -> Boolean): Boolean {
        val components = line.split(' ', '-', ':').toTypedArray()
        val i = components[0].toInt()
        val j = components[1].toInt()
        val char = components[2][0]
        val password = components[4].toCharArray()
        return predicate(password, char, i, j)
    }
}
