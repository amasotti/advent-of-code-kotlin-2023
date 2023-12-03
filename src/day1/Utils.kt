package day1

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/day1/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Represents a map of strings to integers.
*/
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

fun String.findFirstHiddenNumber() : Pair<Int?,Int?> {
    val matches = Regex("(zero|one|two|three|four|five|six|seven|eight|nine)")
    .findAll(this)
    .toList()

    val firstDigit = matches.firstOrNull()?.value?.decodeNumber()
    val index = matches.firstOrNull()?.range?.first

    return Pair(firstDigit, index)
}

fun String.findLastHiddenNumber(): Pair<Int?,Int?> {
    val matches = Regex("(zero|one|two|three|four|five|six|seven|eight|nine)")
    .findAll(this)
    .toList()

    val firstDigit = matches.lastOrNull()?.value?.decodeNumber()
    val index = matches.lastOrNull()?.range?.first

    return Pair(firstDigit, index)
}

fun MutableList<Pair<Int, Int>>.addHiddenNumber(extractedNumber: Pair<Int?, Int?>) {
    if (extractedNumber.first != null && extractedNumber.second != null) {
        add(Pair(extractedNumber.first!!, extractedNumber.second!!))
    }
}
