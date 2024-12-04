import java.io.File

fun main() = day3part2()

fun day3part1() {
//    val memory = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val memory = File("input/day3.txt").readText()

    val instructions = Regex("mul\\((\\d+),(\\d+)\\)").findAll(memory)
        .onEach { println(it.value) }

    val sum = evaluateSums(instructions)
    println("valid instructions $sum")
}

private fun evaluateSums(instructions: Sequence<MatchResult>): Int {
    return instructions.sumOf {
        it.groups[1]?.value!!.toInt() * it.groups[2]?.value!!.toInt()
    }
}

fun day3part2() {
//    val memory = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    val memory = File("input/day3.txt").readText()

    val allInstructions = Regex("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)").findAll(memory)
        .onEach { println(it.value) }

    var enabled = true
    val enabledInstructions = mutableListOf<MatchResult>()
    for (instruction in allInstructions) {
        if (instruction.value.contains("don't")) {
            enabled = false
            continue
        }

        if (instruction.value.contains("do")) {
            enabled = true
            continue
        }

        if (enabled) {
            enabledInstructions.add(instruction)
        }
    }

    val sum = evaluateSums(enabledInstructions.asSequence())
    println("enabled sums $sum")
}