fun calculateDay1a(input: List<String>): Int {
    return input.sumOf { s -> calibrateA(s) }
}

fun calibrateA(input: String): Int {
    val firstDigit = input.first { it.isDigit() }
    val lastDigit = input.reversed().first { it.isDigit() }
    return firstDigit.digitToInt() * 10 + lastDigit.digitToInt()
}


fun calculateDay1b(input: List<String>): Int {
    return input.sumOf { s -> calibrateB(s) }
}

fun calibrateB(input: String): Int {
    val digits = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    val firstDigit = calibrateB(input, digits)
    val lastDigit = calibrateB(input.reversed(), digits.mapKeys { it.key.reversed() })
//    println("Input: $input | Result: ${(firstDigit.toString() + lastDigit.toString()).toInt()}")
    return firstDigit * 10 + lastDigit
}

fun calibrateB(input: String, digits: Map<String, Int>): Int {
    for (i in input.indices) {
        for (digit in digits.entries) {
            if (input.substring(0, i).contains(digit.key)) {
                return digit.value
            }
        }
        if (input[i].isDigit()) {
            return input[i].toString().toInt()
        }
    }
    return 0 // should not appear
}

