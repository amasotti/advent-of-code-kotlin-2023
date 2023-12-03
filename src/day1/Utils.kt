package day1

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path("src/day1/$name.txt").readLines()

/** Represents a map of strings to integers. */
val stringIntMap = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun String.decodeNumber(): Int? {
    return stringIntMap[this.lowercase()]
}

fun String.findFirstHiddenNumber(): Pair<Int?, Int?> {
    val matches = Regex("(zero|one|two|three|four|five|six|seven|eight|nine)").findAll(this).toList()

    val firstDigit = matches.firstOrNull()?.value?.decodeNumber()
    val index = matches.firstOrNull()?.range?.first

    return Pair(firstDigit, index)
}

fun String.findLastHiddenNumber(): Pair<Int?, Int?> {
    val matches = Regex("(one|two|three|four|five|six|seven|eight|nine)").findAll(this).toList()

    val firstDigit = matches.lastOrNull()?.value?.decodeNumber()
    val index = matches.lastOrNull()?.range?.first

    return Pair(firstDigit, index)
}

fun MutableList<Pair<Int, Int>>.addHiddenNumber(extractedNumber: Pair<Int?, Int?>) {
    if (extractedNumber.first != null && extractedNumber.second != null) {
        add(Pair(extractedNumber.first!!, extractedNumber.second!!))
    }
}

/**
 * This was my second attempt (see above ext. functions to find hidden
 * numbers). The regex approach is more clean, since it allows me to
 * generalize the search in the main file
 *
 * But I have to trick it a little bit, searching for the first and last
 * occurrence of the english number names as substrings. One error I needed
 * quite a while to figure out was given by strings like "1sdasfiveightsd"
 * Here the last number is 8 and not 5, but if I proceed in order I will
 * replace five with 5 and then eight won't be found anymore (the "e" was
 * eaten by "fivE").
 */
fun String.replaceStringInt(preferLast: Boolean = false): String {


    val matches = mutableListOf<MatchResult>()

    stringIntMap.keys.forEach { s ->
        matches.addAll(
            Regex(s, RegexOption.IGNORE_CASE).findAll(this).toList()
        )
    }

    // As in part 1 - no numbers hidden with their english names
    if (matches.isEmpty()) {
        return this
    }

    var result = this

    // To avoid the problem described above, sort either from the beginning or from the end
    val sortedMatches = if (preferLast) {
        matches.sortedByDescending { it.range.first }
    } else {
        matches.sortedBy { it.range.first }
    }

    sortedMatches.forEach { match ->
        val number = match.value.decodeNumber()!!
        result = result.replace(match.value, number.toString())
    }

    return result
}


fun String.findCalibrationNumber(): Int {
    val digitIndexedSet = mutableSetOf<Pair<Int, Int>>()

    // Replace from the beginning
    digitIndexedSet.addAll(this.replaceStringInt().extractDigitsIndexed())

    // Replace from the end
    digitIndexedSet.addAll(this.replaceStringInt(true).extractDigitsIndexed())

    // Use the indices to find the first real and last real digit
    val firstDigit = digitIndexedSet.minBy { it.second }.first
    val lastDigit = digitIndexedSet.maxBy { it.second }.first

    val calibrationDigit = "${firstDigit}${lastDigit}".toInt()
    return calibrationDigit
}

/**
 * Go through the string, check each char if it's a digit, return the all
 * the digits found with their index in the string.
 */
fun String.extractDigitsIndexed() = this.mapIndexedNotNull { index, char ->
        char.digitToIntOrNull()?.let { digit ->
            Pair(digit, index)
        }
    }.toMutableSet()
