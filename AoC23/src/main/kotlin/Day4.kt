
import kotlin.math.pow



fun calculateDay4a(input: List<String>): Int {
    var result = 0
    for (line in input) {
        var counter = 0.0
        val game = line.split(':')[1]
        val numbersIHave = game.split('|')[0].trim().split("\\s+".toRegex()).map { it.toInt() }
        val winningNumbers = game.split('|')[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        for (numberIHave in numbersIHave) {
            if (winningNumbers.contains(numberIHave)) {
                counter++
            }
        }
        result += 2.0.pow(counter-1).toInt()
    }

    return result
}




var cards: List<Card> = mutableListOf()


fun calculateDay4b(input: List<String>): Int {
    cards = parseCards(input)
    for (card in cards) {
        playCard(card)
    }
    return cards.sumOf{ it.instances }
}

fun playCard(card: Card) {
    card.instances++
    for (nextIndex in card.index+1..card.index + card.wins) {
       playCard(cards[nextIndex])
    }
}

fun countWinningNumbers(card: Card): Int {
    var countWinningNumbers = 0
    for (numberIHave in card.numbersIHave) {
        if (card.winningNumbers.contains(numberIHave)) {
            countWinningNumbers++
        }
    }
    return countWinningNumbers
}


fun parseCards(input: List<String>): List<Card> {
    val cards = mutableListOf<Card>()
    for (lineIndex in input.indices) {
        val game = input[lineIndex].split(':')[1]
        val numbersIHave = game.split('|')[0].trim().split("\\s+".toRegex()).map { it.toInt() }
        val winningNumbers = game.split('|')[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        val card = Card(lineIndex, numbersIHave, winningNumbers)
        card.wins = countWinningNumbers(card)
        cards.add(card)
    }
    return cards
}


data class Card (
    val index: Int,
    val numbersIHave: List<Int>,
    val winningNumbers: List<Int>,
    var wins: Int = 0,
    var instances: Int = 0
)
