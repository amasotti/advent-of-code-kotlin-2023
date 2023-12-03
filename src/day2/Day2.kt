package day2

import shared.printSectionSeparator

fun main() {
    part1(12,13,14)
}

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
