package day3


fun part1() {
    val matrix = parseInputToMatrix()
    val indexedNums = getNumbersAndIndex()

    val sumOfPartNumbers = indexedNums.sumPartNumbers(matrix)
    check(sumOfPartNumbers == 531561) { "Wrong answer: $sumOfPartNumbers" }
    println(indexedNums.sumPartNumbers(matrix))
}

fun part2() {
TODO()
}


fun main() {
    part1()
}





