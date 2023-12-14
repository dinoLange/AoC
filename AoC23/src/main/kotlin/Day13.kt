fun calculateDay13a(input: List<String>): Int {

    val outputList = input.fold(mutableListOf(mutableListOf<String>())) { acc, item ->
        if (item.isBlank()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(item)
        }
        acc
    }.filter { it.isNotEmpty() }
    for (strings in outputList) {

        runChecks(strings)
    }
    return 0
}

fun runChecks(patternString: MutableList<String>) {
    val pattern: Array<Array<Char>> = patternString.map { it.toCharArray().toTypedArray() }.toTypedArray()

    for (lineIndex in pattern.indices) {
        if (input[lineIndex] == input[lineIndex + 1]) {
            if (checkSymmetry(input.toMutableList(), lineIndex)) {
                return lineIndex
            }
        }
    }
    return 0
}

fun checkHorizontalSymmetry(input: MutableList<String>, lineIndex: Int): Boolean {
    if (lineIndex == 0) {
        return true
    }
    if (input[lineIndex] != input[lineIndex + 1]) {
        return false
    }
    input.removeAt(lineIndex)
    input.removeAt(lineIndex)
    return checkSymmetry(input, lineIndex - 1)
}
