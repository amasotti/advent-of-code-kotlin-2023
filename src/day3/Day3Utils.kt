package day3


import shared.readAdventInput

const val DAY = 3
const val FILE = "Day3"


/**
 * Parses the input file into a matrix of characters
 *
 * My idea here is that perhaps we can check where there are numbers
 * and then check in all directions for a simbol that is not a dot
 */
fun parseInputToMatrix(): Matrix {
    val input = readAdventInput(DAY, FILE)
    return Matrix(input.map { it.toList() })
}

fun getNumbersAndIndex(): List<Number> {
    val input = readAdventInput(DAY, FILE)
    val indexedNumbers = mutableListOf<Number>()

    input.forEachIndexed { rowIndex, line ->
        val pattern = Regex("[0-9]+")
        val matches = pattern.findAll(line)
        matches.forEach { match ->
            val start = Coordinate(rowIndex, match.range.first)
            val end = Coordinate(rowIndex, match.range.last)
            val n = Number(match.value.toInt(), start, end)
            indexedNumbers.add(n)
        }
    }

    return indexedNumbers
}

fun Char.isValidSymbol() = !this.isDigit() && this != '.'

fun Matrix.prettyPrintMatrix(transform: (row: Int, col: Int) -> String) {
    matrix.forEachIndexed { row, line ->
        line.forEachIndexed { col, _ ->
            print(transform(row, col))
        }
        println()
    }
}


/**
 * Dummy debugging tool: it prints the matrix to the console but I have the
 * chance to color or modify the output of each cell to mark part numbers
 */
fun printMatrixMarkingPartNumbers() {
    val matrix = parseInputToMatrix()
    val indexedNums = getNumbersAndIndex()

    val numbers = indexedNums.map { it.number }
    println(numbers)

    val partNumbers = indexedNums.filter { it.isPartNumber(matrix) }

    matrix.prettyPrintMatrix { row, col ->
        val coordinate = Coordinate(row, col)
        val isPartNumber = partNumbers.any { it.start == coordinate || it.end == coordinate }
        if (isPartNumber) "\u001B[31m${matrix[coordinate]}\u001B[0m" else matrix[coordinate].toString()
    }

}
