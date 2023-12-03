package day3


data class Matrix(val matrix: List<List<Char>>) {

    fun isGear(coord: Coordinate): Boolean {
        val cell = this[coord] ?: return false
        if (cell != '*') return false

        return coord.neighbors().count { this[it]?.isDigit() == true } >= 2
    }

    fun findGearRatios(numbers: List<Number>): Int {

        var gearRatioSum = 0
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                val coord = Coordinate(row, col)
                if (isGear(coord)) {
                    gearRatioSum += gearRatio(coord, numbers)
                }
            }
        }

        return gearRatioSum
    }

    operator fun get(coordinate: Coordinate): Char? = matrix.getOrNull(coordinate.row)?.getOrNull(coordinate.col)
}


