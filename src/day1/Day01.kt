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
     * Task 2 - Numbers could be hidden as english names of the digits. Find
     * the first and last occurrence of numbers, be them char digits or english
     * names of the digits and add them together
     */
    fun part2(input: List<String>): Int {
        val digits = mutableListOf<Int>()

        input.map { line ->
            val calibrationDigit = line.findCalibrationNumber()
            println("Input: $line --- Calibration digit: $calibrationDigit")
            digits.add(calibrationDigit)
        }

        check(digits.size == input.size) { "Not all lines were processed" }
        println("Digits sum is: ${digits.sum()}")
        return digits.sum()
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01")
    // check(part1(testInput) == 55208)

    val input = readInput("Day01")

    println(part1(input))
    println(part2(input))

    check(part1(input) == 55208)
    check(part2(input) == 54578)
}
