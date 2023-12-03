package day3

data class Coordinate(val row: Int, val col: Int)
data class Matrix(val matrix: List<List<Char>>) {
    operator fun get(coordinate: Coordinate): Char? =
        matrix.getOrNull(coordinate.row)?.getOrNull(coordinate.col)
}

data class NumberInMatrix(val number: Int, val start: Coordinate, val end: Coordinate) {
    infix fun inBounds(coordinate: Coordinate): Boolean =
        (start.row..end.row).contains(coordinate.row) && (start.col..end.col).contains(coordinate.col)

    infix fun inExpandedBounds(coordinate: Coordinate): Boolean =
        (start.row - 1..end.row + 1).contains(coordinate.row) && (start.col - 1..end.col + 1).contains(coordinate.col)
}


fun NumberInMatrix.isPartNumber(matrix: Matrix): Boolean {
    //print("Checking if $number (between $start and $end) is a part number")

    val rows = (start.row - 1..end.row + 1)
    val cols = (start.col - 1..end.col + 1)

    val result = rows.any { row ->
        cols.any {col ->
            if (col !in start.col..end.col || row !in start.row..end.row)  {
                matrix[Coordinate(row, col)]?.isValidSymbol() ?: false
            } else false

        }
    }

    //println("\n^\tResult: $result")
    return result
}



fun List<NumberInMatrix>.sumPartNumbers(matrix: Matrix): Int {
    var sumOfPartNumbers = 0
    this.forEach {
        if (it.isPartNumber(matrix)) {
            sumOfPartNumbers += it.number
        }
    }
    return sumOfPartNumbers
}

fun Coordinate.neighbors() = listOf(
    Coordinate(row - 1, col), // up
    Coordinate(row + 1, col), // down
    Coordinate(row, col - 1), // left
    Coordinate(row, col + 1), // right
    Coordinate(row - 1, col - 1), // up-left
    Coordinate(row - 1, col + 1), // up-right
    Coordinate(row + 1, col - 1), // down-left
    Coordinate(row + 1, col + 1) // down-right
)



fun Matrix.findGearRations(numbers: List<NumberInMatrix>) : Int {

    fun Matrix.isGear(coord: Coordinate): Boolean {

        if (this[coord] != '*') return false

        val neighbours = coord.neighbors()

        val matrixValuesAtNeighbours = neighbours.map { this[it] }

       val isGear = matrixValuesAtNeighbours.
           filterNotNull()
              .filter { it.isDigit() }
                .count() >= 2

        return isGear
    }

    fun Matrix.gearRatio(coord: Coordinate): Int {

        val candidates = numbers.filter { number ->
            number inExpandedBounds coord
        }

        if (candidates.size != 2) return 0

        val number1 = candidates[0].number
        val number2 = candidates[1].number

        return number1 * number2
    }

    var gearRatioSum = 0
    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            val coord = Coordinate(row, col)
            if (isGear(coord)) {
                gearRatioSum += gearRatio(coord)
            }
        }
    }

    return gearRatioSum
}
