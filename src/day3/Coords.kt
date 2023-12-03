package day3

data class Coordinate(val row: Int, val col: Int)

data class Matrix(val matrix: List<List<Char>>) {
    operator fun get(coordinate: Coordinate): Char? =
        matrix.getOrNull(coordinate.row)?.getOrNull(coordinate.col)
}

data class NumberInMatrix(val number: Int, val start: Coordinate, val end: Coordinate)


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
