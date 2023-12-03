package day3


fun part1(inputMatrix: Matrix, indexedNums: List<Number>) {
    val sumOfPartNumbers = indexedNums.sumPartNumbers(inputMatrix)

    // check(part1Result == 4361) { "Wrong answer: $part1Result" } // test input
    check(sumOfPartNumbers == 531561) { "Wrong answer: $sumOfPartNumbers" }
    println(sumOfPartNumbers)
}

fun part2(inputMatrix: Matrix, indexedNums: List<Number>) {
    val filteredIndexedNums = indexedNums.filter { it.isPartNumber(inputMatrix) }
    val sumOfGears = inputMatrix.findGearRatios(filteredIndexedNums)

    check(sumOfGears == 83279367) { "Wrong answer: $sumOfGears" }
    println(sumOfGears)
}


fun main() {
    val inputMatrix = parseInputToMatrix()
    val indexedNums = getNumbersAndIndex()

    part1(inputMatrix, indexedNums)
    part2(inputMatrix, indexedNums)
}


