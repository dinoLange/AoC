import kotlin.math.sqrt

fun calculateDay9a(input: List<String>): Int {
    val sequences = input.map { line -> line.split(" ").map { it.toInt() } }
    return sequences.sumOf { extrapolate(it) }
}

fun calculateDay9b(input: List<String>): Int {
    val sequences = input.map { line -> line.split(" ").filter { it.isNotBlank() }.map { it.toInt() } }
    val list = mutableListOf<Int>()
    for (sequence in sequences) {
        println("")
        println("")
        println("New History: ")
        println("")
        val extrapolateBackwards = extrapolateBackwards(sequence)
        println("Value: $extrapolateBackwards")
        list.add(extrapolateBackwards)
    }
    return list.sum()
}

fun extrapolateBackwards(sequence: List<Int>): Int {
    val differenceLevels = mutableListOf<List<Int>>()
    differenceLevels.add(sequence)
    calcLevels(sequence, differenceLevels)
    prettyPrint(differenceLevels)
    var value = 0
    for (differences in differenceLevels.reversed()) {
        value = differences.first() - value
        print("$value, ")
    }
    return value
}

fun extrapolate(sequence: List<Int>): Int {
    val differenceLevels = mutableListOf<List<Int>>()
    differenceLevels.add(sequence)
    calcLevels(sequence, differenceLevels)
    prettyPrint(differenceLevels)
    var value = 0
    for (differences in differenceLevels.reversed()) {
        value += differences.last()
    }
    return value
}


fun calcLevels(currentSequence: List<Int>, levels: MutableList<List<Int>>) {
    val calcDifferences = calcDifferences(currentSequence)
    levels.add(calcDifferences)
    if (calcDifferences.all { it == 0 }) {
        return
    }
    calcLevels(calcDifferences, levels)
}

fun calcDifferences(sequence: List<Int>): List<Int> {
    val differences = mutableListOf<Int>()

    for (index in sequence.indices) {
        if (index == sequence.size - 1) {
            break
        }
        differences.add(sequence[index + 1] - sequence[index])
    }
    return differences
}


fun prettyPrint(differenceLevels: MutableList<List<Int>>) {

    var count = 0
    for (differenceLevel in differenceLevels) {
        println("    ".repeat(count) + differenceLevel.joinToString("    "))
        count++
    }
}
