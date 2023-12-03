package day2

import shared.readAdventInput


fun parseGames(): List<Game> {
    val record = readAdventInput(2, "Day2")

    return record.map { Game.fromRecord(it) }
}

fun Round.canBePlayedWith(redCubes: Int,greenCubes: Int,blueCubes: Int) =
    this.redCubes <= redCubes
            && this.greenCubes <= greenCubes
            && this.blueCubes <= blueCubes


fun sumIdsOfCompatibleGames(games: List<Game>) = games.sumOf { it.id }

