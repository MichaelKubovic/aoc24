import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() = day1b()

fun day1() {
    val numbers = readNumbers()

    val lefts = numbers.first.sorted()
    val rights = numbers.second.sorted()

    val sum = lefts.zip(rights).asSequence().map {
        max(it.first, it.second) - min(it.first, it.second)
    }.sum()

    println("sum of diffs is $sum")
}

private fun readNumbers(): Pair<List<Int>, List<Int>> {
    val lines = File("input/day1.txt").readLines()

    val numbers = lines.asSequence().map {
        val ints = it.split("   ").map { it.toInt() }
        Pair(ints[0], ints[1])
    }.unzip()
    return numbers
}

fun day1b() {
    val numbers = readNumbers()

    val similarityScore = numbers.first.asSequence().map {
        val needle = it
        val found = numbers.second.asSequence().filter { it == needle }.count()
        it * found
    }.sum()

    println("similarity score $similarityScore")
}