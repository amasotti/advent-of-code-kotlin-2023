package shared

import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readAdventInput(day: Int, name: String) = Path("src/day$day/$name.txt").readLines()


fun printSectionSeparator() = println("".padEnd(50, '-'))
