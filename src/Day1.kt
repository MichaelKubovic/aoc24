import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    val lines = File("input/day1.txt").readLines()

    val numbers = lines.asSequence().map {
        val ints = it.split("   ").map { it.toInt() }
        Pair(ints[0], ints[1])
    }.unzip()

    val lefts = numbers.first.sorted()
    val rights = numbers.second.sorted()

    val sum = lefts.zip(rights).asSequence().map {
        max(it.first, it.second) - min(it.first, it.second)
    }.sum()

    println("sum of diffs is $sum")
}