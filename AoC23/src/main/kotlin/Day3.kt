fun calculateDay3a(input: List<String>): Int {
    val findValidNumbers = findValidNumbers(input)
    return findValidNumbers.sumOf { it.toInt() }
}

fun findValidNumbers(input: List<String>): MutableList<String> {
    val rows = input.size
    val cols = input[0].length
    val charMatrix: Array<Array<Char>> = Array(rows) { Array(cols) { ' ' } }

    for (rowIndex in input.indices) {
        val toCharArray: Array<Char> = input[rowIndex].toCharArray().toTypedArray()
        charMatrix[rowIndex] = toCharArray
    }

    val numbers = mutableListOf<String>()

    var currentNumber = ""
    for (row in 0 until rows) {
        var start = -1
        for (col in 0 until cols) {
            if (charMatrix[row][col].isDigit()) {
                if (start == -1) {
                    start = col
                }
                currentNumber += charMatrix[row][col]
            }
            if (col + 1 == cols || !charMatrix[row][col + 1].isDigit()) {
                if (currentNumber.isEmpty()) {
                    continue
                }
                if (areSymbolsAdjacent(charMatrix, start, col, row)) {
                    numbers.add(currentNumber)
                }
                currentNumber = "";
                start = -1
            }
        }
    }
    return numbers
}

fun areSymbolsAdjacent(charMatrix: Array<Array<Char>>, start: Int, end: Int, row: Int): Boolean {
    for (colPos in start..end) {
        for (rowOffset in -1..1) {
            for (colOffset in -1..1) {
                if (row + rowOffset < 0 || colPos + colOffset < 0) {
                    continue
                }
                if (row + rowOffset >= charMatrix.size || colPos + colOffset >= charMatrix[0].size) {
                    continue
                }
                val adjacentChar = charMatrix[row + rowOffset][colPos + colOffset]
                if (!adjacentChar.isDigit() && adjacentChar != '.') {
                    return true
                }
            }
        }
    }
    return false
}


fun calculateDay3b(input: List<String>): Int {
    val gearRatios = findGearRatiosNumbers(input)
    return gearRatios.sumOf { it }
}

data class NumberPosition(
    var numberString: String,
    var row: Int,
    var start: Int,
)


fun findGearRatiosNumbers(input: List<String>): List<Int> {
    val rows = input.size
    val cols = input[0].length
    val charMatrix: Array<Array<Char>> = Array(rows) { Array(cols) { ' ' } }

    for (rowIndex in input.indices) {
        val toCharArray: Array<Char> = input[rowIndex].toCharArray().toTypedArray()
        charMatrix[rowIndex] = toCharArray
    }

    val numbers = findAllNumbers(charMatrix)
    val gearRatios = mutableListOf<Int>()

    for (row in charMatrix.indices) {
        for (col in 0 until charMatrix[0].size) {
            if (charMatrix[row][col] == '*') {
                val adjacentNumbers: List<NumberPosition> = numbers.filter { isNumberAdjacent(it, row, col) }
                if (adjacentNumbers.size == 2) {
                    gearRatios.add(adjacentNumbers[0].numberString.toInt() * adjacentNumbers[1].numberString.toInt())
                }
            }
        }
    }
    return gearRatios
}

fun isNumberAdjacent(number: NumberPosition, row: Int, col: Int): Boolean {
    if ((number.row - 1..number.row + 1).contains(row)) {
        if ((number.start - 1..number.start + number.numberString.length).contains(col)) {
            return true
        }
    }
    return false
}


fun findAllNumbers(charMatrix: Array<Array<Char>>): MutableList<NumberPosition> {
    val numbers = mutableListOf<NumberPosition>()
    var currentNumber = ""
    for (row in charMatrix.indices) {
        var start = -1
        for (col in 0 until charMatrix[0].size) {
            if (charMatrix[row][col].isDigit()) {
                if (start == -1) {
                    start = col
                }
                currentNumber += charMatrix[row][col]
            }
            if (col + 1 == charMatrix[0].size || !charMatrix[row][col + 1].isDigit()) {
                if (currentNumber.isEmpty()) {
                    continue
                }
                numbers.add(NumberPosition(currentNumber, row, start))
                currentNumber = "";
                start = -1
            }
        }
    }
    return numbers
}
