package day3

data class Number(val number: Int, val start: Coordinate, val end: Coordinate) {
    infix fun inBounds(coordinate: Coordinate): Boolean =
        (start.row..end.row).contains(coordinate.row) && (start.col..end.col).contains(coordinate.col)

    infix fun inExpandedBounds(coordinate: Coordinate): Boolean =
        (start.row - 1..end.row + 1).contains(coordinate.row) && (start.col - 1..end.col + 1).contains(coordinate.col)
}

fun Number.isPartNumber(matrix: Matrix): Boolean {
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

fun List<Number>.sumPartNumbers(matrix: Matrix): Int {
    var sumOfPartNumbers = 0
    this.forEach {
        if (it.isPartNumber(matrix)) {
            sumOfPartNumbers += it.number
        }
    }
    return sumOfPartNumbers
}
