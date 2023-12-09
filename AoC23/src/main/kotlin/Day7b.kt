fun calculateDay7b(input: List<String>): Int {
    val hands: List<CamelHand> = input.map { createCamelHand(it.split(" ")[0], it.split(" ")[1]) }.toList()
    for (hand in hands) {
        hand.type = figureOutHandTypeB(hand) // test this
    }
    val sortedHands: List<CamelHand> = sortHandsByStrength(hands).reversed()
    for (camelHand in sortedHands) {
        println("Cards ${camelHand.hand.joinToString { it.symbol.toString() }} are of type ${camelHand.type}")

    }

    var sum = 0
    for (index in sortedHands.indices) {
        sum += sortedHands[index].bid * (index + 1)
    }
    return sum
}

private fun sortHandsByStrength(hands: List<CamelHand>): List<CamelHand> {
    return hands.sortedWith { a, b ->
        when {
            isHandBetter(a, b) -> 1
            else -> -1
        }
    }
}
private fun isHandBetter(handA: CamelHand, handB: CamelHand): Boolean {
    if (handA.type == handB.type) {
        for (index in handA.hand.indices) {
            val cardA = handA.hand[index]
            val cardB = handB.hand[index]
            if (cardA != cardB) {
                return cardA.bOrder > cardB.bOrder
            }
        }
    }
    return handA.type.ordinal > handB.type.ordinal
}

fun figureOutHandTypeB(camelHand: CamelHand): HandType {
    val countMap: Map<CamelCard, Int> =
        camelHand.hand.toSet().filter { it != CamelCard.JACK }.associateWith { camelHand.hand.count { that -> it == that} }
    val jokers = camelHand.hand.count { it == CamelCard.JACK }
    return when {
        jokers == 5 || countMap.values.max() + jokers == 5 -> HandType.FIVE_OF_A_KIND
        countMap.values.max() + jokers == 4 -> HandType.FOUR_OF_A_KIND
        isFullHouse(jokers, countMap) -> HandType.FULL_HOUSE
        countMap.containsValue(3) || (countMap.containsValue(2) && jokers > 0) || jokers == 2 -> HandType.THREE_OF_A_KIND
        isTwoPairB(countMap) -> HandType.TWO_PAIR
        countMap.containsValue(2) || jokers > 0 -> HandType.ONE_PAIR
        else -> HandType.HIGH_CARD
    }
}

fun isFullHouse(jokers: Int, countMap: Map<CamelCard, Int>): Boolean {
    if (countMap.containsValue(3) && countMap.containsValue(2)) {
        return true
    }
    return isTwoPairB(countMap) && jokers == 1
}


fun isTwoPairB(countMap: Map<CamelCard, Int>): Boolean {
    var count = 0
    for (entry in countMap.entries) {
        if (entry.value == 2) {
            count++
        }
    }
    return count == 2
}


