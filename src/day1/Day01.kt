package day1

fun main() {
    fun part1(input: List<String>): Int {

        val digits = mutableListOf<Int>()

        input.forEach { line ->
            val firstDigit = line.find { it.isDigit() }.toString()
            val lastDigit = line.findLast { it.isDigit() }.toString()

            digits.add((firstDigit + lastDigit).toInt())
        }

        return digits.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01")
    check(part1(testInput) == 55208)

//    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}
