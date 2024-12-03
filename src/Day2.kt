import java.io.File

fun main() = day2()

fun day2() {
    val lines = File("input/day2.txt").readLines()
    val safeReports = lines.asSequence().map(::evaluateReport).filter { it }.count()
    println("safe reports $safeReports")
}

private fun evaluateReport(report: String): Boolean {
    val levels = report.split(" ").map(String::toInt)

    if (levels != levels.sorted() && levels != levels.sortedDescending()) {
        return false
    }

    return levels.indices.take(levels.size - 1).map {
        val level = levels[it]
        val nextLevel = levels[it + 1]

        if (level > nextLevel) level - nextLevel else nextLevel - level
    }.fold(true) {
        acc, diff -> acc && diff >= 1 && diff <= 3
    }
}