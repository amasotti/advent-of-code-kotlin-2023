package day2

import shared.printSectionSeparator

fun main() {
    part1(12,13,14)
    part2()
}

/**
 * Check if a game can be played with the given cubes or you would need more.
 */
fun part1(red: Int, green: Int, blue: Int) {
    val games = parseGames()

    val compatibleGames = games.filterNot { game ->
        game.rounds.any { round ->
            round.canBePlayedWith(red, green, blue).not()
        }
    }

    println("Compatible games with $red red, $green green and $blue blue cubes:")
    compatibleGames.forEach { println(it) }
    printSectionSeparator()

    val sum = sumIdsOfCompatibleGames(compatibleGames)
    println("Sum of compatible games: $sum")
    printSectionSeparator()
}


/**
 * What's the minimum amount of cubes you need to play all in the records?
 */
fun part2() {
    val games = parseGames()

    // Find the min needed cubes
    val minCubes = mutableListOf<Round>()
    games.forEach { game ->
        val minRedCubes = game.rounds.maxOf { it.redCubes }
        val minGreenCubes = game.rounds.maxOf { it.greenCubes }
        val minBlueCubes = game.rounds.maxOf { it.blueCubes }

        minCubes.add(Round(minRedCubes,minGreenCubes,minBlueCubes))
    }
    minCubes.forEach { println(it) }

    // Sum the "power" of cubes
    val sumOfCubesPower = minCubes.sumOf { it.power() }

    println("Sum of cubes power: $sumOfCubesPower")

}
