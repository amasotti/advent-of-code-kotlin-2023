package day2

data class Round(
    val blueCubes: Int = 0,
    val redCubes: Int = 0,
    val greenCubes: Int = 0,
) {

    override fun toString(): String {
        return "\tIteration with ${totalCubes()} cubes (${redCubes}R,${greenCubes}G,${blueCubes}B)\n"
    }

    fun totalCubes(): Int = blueCubes + redCubes + greenCubes

    fun power(): Int = redCubes * greenCubes * blueCubes
}

data class Game(val id: Int,val rounds: List<Round>)
{
    fun totalInterations(): Int = rounds.size

    override fun toString(): String {

        val iterationRepr = rounds.joinToString(separator = "") { it.toString() }.prependIndent("\t")

        return "Game $id: \n" +
                "\tTotal Rounds: ${totalInterations()}\n" +
                "\tRounds:\n" + iterationRepr
    }


    companion object {

        /**
         * Extract the information about a game and the iterations of revelead cubes from the text input
         *
         * Ex. Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
         */
        fun fromRecord(record: String): Game {
         val id = record.substringBefore(":").replace("Game", "").trim().toInt()
         val iterationRecords = record.substringAfter(":").split(";")

            val iterations = iterationRecords.map { iterationRecord ->
                val cubes = iterationRecord.split(",")
                val blueCubes = cubes.find { it.contains("blue") }?.substringBefore("blue")?.trim()?.toInt() ?: 0
                val redCubes = cubes.find { it.contains("red") }?.substringBefore("red")?.trim()?.toInt() ?: 0
                val greenCubes = cubes.find { it.contains("green") }?.substringBefore("green")?.trim()?.toInt() ?: 0
                Round(blueCubes, redCubes, greenCubes)
            }
            return Game(id, iterations)
     }

    }
}


