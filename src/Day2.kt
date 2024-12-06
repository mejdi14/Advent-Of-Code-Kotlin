import Day02.Part1
import Day02.Part2
import kotlin.math.absoluteValue

fun main() {
    val input = readInput(day = 2)
    println("Part 1: ${Part1.calculateNumSafeReports(input)}")
    println("Part 2: ${Part2.calculateNumSafeReportsWithProblemDampener(input)}")
}

private object Day02 {

    object Part1 {

        fun calculateNumSafeReports(input: List<String>): Int =
            parseReports(input)
                .count(::isReportSafe)

        fun isReportSafe(report: List<Int>): Boolean {
            var lastChange: Boolean? = null
            for (i in 0 until (report.size - 1)) {
                val first = report[i]
                val second = report[i + 1]
                val difference = first - second
                when {
                    ((difference == 0) || (difference.absoluteValue > 3)) -> return false
                    (lastChange == null) -> {
                        lastChange = difference > 0
                    }
                    (lastChange && difference < 0 || !lastChange && difference > 0) -> return false
                }
            }
            return true
        }

    }

    object Part2 {

        fun calculateNumSafeReportsWithProblemDampener(input: List<String>): Int =
            parseReports(input)
                .count(::isReportSafeWithProblemDampener)

        fun isReportSafeWithProblemDampener(report: List<Int>): Boolean {
            if (Part1.isReportSafe(report)) {
                return true
            } else {
                for (i in report.indices) {
                    val dampenedReport = report.toMutableList()
                    dampenedReport.removeAt(i)
                    if (Part1.isReportSafe(dampenedReport)) {
                        return true
                    }
                }
            }
            return false
        }
    }

    fun parseReports(input: List<String>): List<List<Int>> = buildList {
        input.forEach { line ->
            add(
                line.split(" ")
                    .map { it.toInt() }
            )
        }
    }
}