import Day04.Part1
import Day04.Part2

fun main() {
    val input = readInput(day = 4)
    println("Part 1: ${Part1.calculateNumberOfWords(input)}")
    println("Part 2: ${Part2.calculateNumberOfXmasCrosses(input)}")
}

private object Day04 {

    object Part1 {
        private const val Word = "XMAS"
        private const val ReverseWord = "SAMX"

        fun calculateNumberOfWords(input: List<String>): Int {
            var sum = 0
            for (row in 0..input.lastIndex) {
                for (col in 0..<input[row].length) {
                    sum += wordCountForGridStep(input, col, row)
                }
            }
            return sum
        }

        private fun wordCountForGridStep(input: List<String>, col: Int, row: Int): Int =
            horizontalWordCount(input, col, row) +
                    verticalWordCount(input, col, row) +
                    diagonalWordCount(input, col, row)

        private fun horizontalWordCount(input: List<String>, col: Int, row: Int): Int {
            val endCol = col + 3
            return if (endCol < input[row].length) {
                val word =
                    "${input[row][col]}${input[row][col + 1]}${input[row][col + 2]}${input[row][col + 3]}"
                if (word == Word || word == ReverseWord) {
                    1
                } else 0
            } else 0
        }

        private fun verticalWordCount(input: List<String>, col: Int, row: Int): Int {
            val endRow = row + 3
            return if (endRow < input.size) {
                val word =
                    "${input[row][col]}${input[row + 1][col]}${input[row + 2][col]}${input[row + 3][col]}"
                if (word == Word || word == ReverseWord) {
                    1
                } else 0
            } else 0
        }

        private fun diagonalWordCount(input: List<String>, col: Int, row: Int): Int =
            rightDiagonalWordCount(input, col, row) +
                    leftDiagonalWordCount(input, col, row)

        private fun rightDiagonalWordCount(input: List<String>, col: Int, row: Int): Int {
            val endRow = row + 3
            val endCol = col + 3
            return if (endRow < input.size && endCol < input[row].length) {
                val word =
                    "${input[row][col]}${input[row + 1][col + 1]}${input[row + 2][col + 2]}${input[row + 3][col + 3]}"
                if (word == Word || word == ReverseWord) {
                    1
                } else 0
            } else 0
        }

        private fun leftDiagonalWordCount(input: List<String>, col: Int, row: Int): Int {
            val endRow = row + 3
            val endCol = col - 3
            return if (endRow < input.size && endCol >= 0) {
                val word =
                    "${input[row][col]}${input[row + 1][col - 1]}${input[row + 2][col - 2]}${input[row + 3][col - 3]}"
                if (word == Word || word == ReverseWord) {
                    1
                } else 0
            } else 0
        }
    }

    object Part2 {
        private const val Word = "MAS"
        private const val ReverseWord = "SAM"

        fun calculateNumberOfXmasCrosses(input: List<String>): Int {
            var sum = 0
            for (row in 0..input.lastIndex) {
                for (col in 0..<input[row].length) {
                    if (xmasCrossCheckForGridStep(input, col, row)) {
                        sum++
                    }
                }
            }
            return sum
        }

        private fun xmasCrossCheckForGridStep(input: List<String>, col: Int, row: Int): Boolean =
            rightDiagonalXmasCheck(input, col, row) &&
                    leftDiagonalXmasCheck(input, col, row)

        private fun rightDiagonalXmasCheck(input: List<String>, col: Int, row: Int): Boolean {
            val endRow = row + 2
            val endCol = col + 2
            return if (endRow < input.size && endCol < input[row].length) {
                val word =
                    "${input[row][col]}${input[row + 1][col + 1]}${input[row + 2][col + 2]}"
                word == Word || word == ReverseWord
            } else false
        }

        private fun leftDiagonalXmasCheck(input: List<String>, col: Int, row: Int): Boolean {
            val endRow = row + 2
            val startCol = col + 2
            return if (endRow < input.size && startCol < input[row].length) {
                val word =
                    "${input[row][col + 2]}${input[row + 1][col + 1]}${input[row + 2][col]}"
                word == Word || word == ReverseWord
            } else false
        }
    }
}