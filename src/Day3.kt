import java.io.File

fun main() = day3part1()

fun day3part1() {
//    val memory = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val memory = File("input/day3.txt").readText()

    val instructions = Regex("mul\\((\\d+),(\\d+)\\)").findAll(memory)
        .onEach { println(" ${it.groups[1]?.value} x ${it.groups[2]?.value}") }

    val sum = instructions.map {
        it.groups[1]?.value!!.toInt() * it.groups[2]?.value!!.toInt()
    }.sum()

    println("valid instructions $sum")
}