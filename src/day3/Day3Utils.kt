package day3

import shared.readAdventInput

object Constants {
    const val DAY = 3
    const val FILE = "Day3"
}


/**
 * Parses the input file into a matrix of characters
 *
 * My idea here is that perhaps we can check where there are numbers and
 * then check in all directions for a simbol that is not a dot
 */
fun parseInputToMatrix(): Matrix =
    readAdventInput(Constants.DAY, Constants.FILE)
        .map { it.toList() }
        .let { Matrix(it) }

fun getNumbersAndIndex(): List<Number> {
    val input = readAdventInput(Constants.DAY, Constants.FILE)

    return input
        .asSequence()
        .mapIndexed { rowIndex, line -> rowIndex to Regex("[0-9]+").findAll(line) }
        .flatMap { (rowIndex, matches) ->
            matches.map { match ->
                val start = Coordinate(rowIndex, match.range.first)
                val end = Coordinate(rowIndex, match.range.last)
                Number(match.value.toInt(), start, end)
            }
        }
        .toList()
}

fun Char.isValidSymbol(): Boolean = !isDigit() && this != '.'

fun Matrix.prettyPrintMatrix(transform: (Int, Int) -> String) = matrix.forEachIndexed { row, line ->
    line.forEachIndexed { col, _ -> print(transform(row, col)) }
    println()
}


/**
 * Dummy debugging tool: it prints the matrix to the console but I have the
 * chance to color or modify the output of each cell to mark part numbers
 */
fun printMatrixMarkingPartNumbers() {
    val matrix = parseInputToMatrix()
    val numbers = getNumbersAndIndex()

    val partNumbers = numbers.filter { it.isPartNumber(matrix) }

    matrix.prettyPrintMatrix { row, col ->
        val coordinate = Coordinate(row, col)
        val isPartNumber = partNumbers.any { it.start == coordinate || it.end == coordinate }
        if (isPartNumber) "\u001B[31m${matrix[coordinate]}\u001B[0m" else matrix[coordinate].toString()
    }
}


fun gearRatio(coord: Coordinate, numbers: List<Number>): Int {

    val candidates = numbers.filter {number ->
        number inExpandedBounds coord
    }

    if (candidates.size != 2) return 0

    val number1 = candidates[0].number
    val number2 = candidates[1].number

    return number1 * number2
}
