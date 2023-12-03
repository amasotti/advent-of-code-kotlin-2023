package day1

fun main() {
    fun part1(input: List<String>): Int {

        val digits = mutableListOf<Int>()

        input.forEach { line ->
            val firstDigit = line.find { it.isDigit() }?.toString() ?: "0"
            val lastDigit = line.findLast { it.isDigit() }?.toString() ?: "0"

            digits.add("${firstDigit}${lastDigit}".toInt())
        }

        return digits.sum()
    }


    /**
     * Task 2 - Numbers could be hidden as english names of the digits.
     * Find the first and last occurrence of numbers, be them char digits or
     * english names of the digits and add them together
     */
    fun part2(input: List<String>): Int {
        val digits = mutableListOf<Int>()

        input.map { line ->
            val digitsInLineIndexed : MutableList<Pair<Int,Int>> = line
                .mapIndexedNotNull {index, char ->
                    char.digitToIntOrNull()?.let {
                        digit -> Pair(digit, index)
                    }
                }.toMutableList()


            digitsInLineIndexed.addHiddenNumber(line.findFirstHiddenNumber())
            digitsInLineIndexed.addHiddenNumber(line.findLastHiddenNumber())

            // Take the first digit, based on the indices (second element of each pair) and the last digit
            // concatenate them and add them to the list of digits
            val firstDigit = digitsInLineIndexed.minBy { it.second }.first
            val lastDigit = digitsInLineIndexed.maxBy { it.second }.first

            val winningDigit = "${firstDigit}${lastDigit}".toInt()
            digits.add(winningDigit)
        }

        return digits.sum()
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01")
    // check(part1(testInput) == 55208)

    val input = readInput("Day01")

    println(part1(input))
    println(part2(input))

    check(part1(input) == 55208)
    check(part2(input) == 54558)
}
