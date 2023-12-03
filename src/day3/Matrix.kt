package day3

data class Matrix(val matrix: List<List<Char>>) {
    operator fun get(coordinate: Coordinate): Char? =
        matrix.getOrNull(coordinate.row)?.getOrNull(coordinate.col)
}

fun Matrix.findGearRations(numbers: List<Number>) : Int {

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
