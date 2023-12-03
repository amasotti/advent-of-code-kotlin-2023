package day3


fun part1() {
    val matrix = parseInputToMatrix()
    val indexedNums = getNumbersAndIndex()

    val sumOfPartNumbers = indexedNums.sumPartNumbers(matrix)

    // check(sumOfPartNumbers == 4361) { "Wrong answer: $sumOfPartNumbers" } // test input
    check(sumOfPartNumbers == 531561) { "Wrong answer: $sumOfPartNumbers" }

    println(indexedNums.sumPartNumbers(matrix))
}

fun part2() {
    val matrix = parseInputToMatrix()
    val indexedNums = getNumbersAndIndex().filter { it.isPartNumber(matrix) }

    val gearRatioSum = matrix.findGearRatios(indexedNums)

    check(gearRatioSum == 83279367) { "Wrong answer: $gearRatioSum" }
    println(gearRatioSum)
}


fun main() {
    part1()
    part2()
}





