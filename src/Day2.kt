import java.io.File

fun main() = day2part2()

private fun reports(): List<List<Int>> {
    val lines = File("input/day2.txt").readLines()
    return lines.map { it.split(" ").map(String::toInt) }
}

fun day2() {
    val safeReports = reports().map(::evaluateReport).count { it }
    println("safe reports $safeReports")
}

private fun evaluateReport(levels: List<Int>): Boolean {
    if (levels != levels.sorted() && levels != levels.sortedDescending()) {
        return false
    }

    val safe = levels.indices.take(levels.size - 1).map {
        val level = levels[it]
        val nextLevel = levels[it + 1]

        if (level > nextLevel) level - nextLevel else nextLevel - level
    }.fold(true) { acc, diff -> acc && diff in 1..3 }
//    println("levels $levels are safe: $safe")
    return safe
}

fun day2part2() {
    val safeReports = reports().map { report ->
        if (evaluateReport(report)) {
            return@map true
        }

        report.indices.map {
            val dampenedReport = report.toMutableList()
            dampenedReport.removeAt(it);
            dampenedReport
        }
        .map(::evaluateReport)
        .firstOrNull { it } != null
    }.count { it }

    println("safe reports with the Problem Dampener $safeReports")
}
