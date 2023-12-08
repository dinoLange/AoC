fun calculateDay7a(input: List<String>): Int {
    val hands: List<CamelHand> = input.map { createCamelHand(it.split(" ")[0], it.split(" ")[1]) }.toList()
    for (hand in hands) {
        hand.type = figureOutHandType(hand) // test this
    }
    val sortedHands: List<CamelHand> = sortHandsByStrength(hands).reversed()
    var sum = 0
    for (index in sortedHands.indices) {
        sum += sortedHands[index].bid * (index + 1)
    }
    return sum
}

fun figureOutHandType(camelHand: CamelHand): HandType {
    val countMap: Map<CamelCard, Int> =
        camelHand.hand.toSet().associateWith { camelHand.hand.count { that -> it == that } }
    return when {
        countMap.containsValue(5) -> HandType.FIVE_OF_A_KIND
        countMap.containsValue(4) -> HandType.FOUR_OF_A_KIND
        countMap.containsValue(3) && countMap.containsValue(2) -> HandType.FULL_HOUSE
        countMap.containsValue(3) -> HandType.THREE_OF_A_KIND
        isTwoPair(countMap) -> HandType.TWO_PAIR
        countMap.containsValue(2) -> HandType.ONE_PAIR
        else -> HandType.HIGH_CARD
    }
}

fun isTwoPair(countMap: Map<CamelCard, Int>): Boolean {
    var count = 0
    for (entry in countMap.entries) {
        if (entry.value == 2) {
            count++
        }
    }
    return count == 2
}


fun sortHandsByStrength(hands: List<CamelHand>): List<CamelHand> {
    return hands.sortedWith { a, b ->
        when {
            isHandBetter(a, b) -> 1
            else -> -1
        }
    }
}

fun isHandBetter(handA: CamelHand, handB: CamelHand): Boolean {
    if (handA.type == handB.type) {
        for (index in handA.hand.indices) {
            val cardA = handA.hand[index]
            val cardB = handB.hand[index]
            if (cardA != cardB) {
                return cardA.ordinal > cardB.ordinal
            }
        }
    }
    return handA.type.ordinal > handB.type.ordinal
}


fun createCamelHand(handChars: String, bid: String): CamelHand {
    return CamelHand(handChars.map { findCardByChar(it) }.toList(), bid.toInt())
}

fun findCardByChar(cardChar: Char): CamelCard {
    for (value in CamelCard.values()) {
        if (value.symbol == cardChar) {
            return value
        }
    }
    throw IllegalArgumentException("Char $cardChar is no valid camel card")
}

data class CamelHand(
    val hand: List<CamelCard>,
    val bid: Int,
    var type: HandType = HandType.HIGH_CARD,
)


enum class HandType {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD
}

enum class CamelCard(val symbol: Char) {
    ACE('A'),
    KING('K'),
    QUEEN('Q'),
    JACK('J'),
    TEN('T'),
    NINE('9'),
    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2')
}
