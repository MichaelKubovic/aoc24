import java.io.File

fun main() = day4part1()

fun day4part1() {
//    val maze = """
//        MMMSXXMASM
//        MSAMXMSMSA
//        AMXSXMAAMM
//        MSAMASMSMX
//        XMASAMXAMM
//        XXAMMXXAMA
//        SMSMSASXSS
//        SAXAMASAAA
//        MAMMMXMMMM
//        MXMXAXMASX
//    """.trimIndent().lines()
    val maze = File("input/day4.txt").readLines()

    val solutionsPositions = mutableListOf<Pair<Int, Int>>()
    var xmasCounter = 0;
    rows@for (row in maze.indices) {
        letters@for (col in maze[row].indices) {
            if (maze[row][col] == 'X') {
                xmasCounter += xmasSearch(maze, Pair(row, col))
            }
        }
    }

    println("XMAS $xmasCounter")
}

fun xmasSearch(maze: List<String>, x: Pair<Int, Int>): Int {
    var counter = 0
    // right
    if (maze[x.first].length >= x.second + 3 + 1)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first][x.second+1], maze[x.first][x.second+2], maze[x.first][x.second+3]).concatToString()) counter++
    // right-down
    if (maze[x.first].length >= x.second + 3 + 1 && maze.size >= x.first + 3 + 1)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first+1][x.second+1], maze[x.first+2][x.second+2], maze[x.first+3][x.second+3]).concatToString()) counter++
    // down
    if (maze.size >= x.first + 3 + 1)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first+1][x.second], maze[x.first+2][x.second], maze[x.first+3][x.second]).concatToString()) counter++
    // left-down
    if (0 <= x.second - 3 && maze.size >= x.first + 3 + 1)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first+1][x.second-1], maze[x.first+2][x.second-2], maze[x.first+3][x.second-3]).concatToString()) counter++
    // left
    if (0 <= x.second - 3)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first][x.second-1], maze[x.first][x.second-2], maze[x.first][x.second-3]).concatToString()) counter++
    // left-up
    if (0 <= x.second - 3 && 0 <= x.first - 3)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first-1][x.second-1], maze[x.first-2][x.second-2], maze[x.first-3][x.second-3]).concatToString()) counter++
    // up
    if (0 <= x.first - 3)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first-1][x.second], maze[x.first-2][x.second], maze[x.first-3][x.second]).concatToString()) counter++
    // up-right
    if (0 <= x.first - 3 && maze[x.first].length >= x.second + 3 + 1)
        if ("XMAS" == charArrayOf(maze[x.first][x.second], maze[x.first-1][x.second+1], maze[x.first-2][x.second+2], maze[x.first-3][x.second+3]).concatToString()) counter++

    return counter
}