package day3

data class Coordinate(val row: Int, val col: Int) {
    fun isInBound(start: Coordinate, end: Coordinate): Boolean =
        (start.row..end.row).contains(row) && (start.col..end.col).contains(col)
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



