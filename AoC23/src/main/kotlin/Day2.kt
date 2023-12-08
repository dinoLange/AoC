const val maxReds = 12
const val maxGreens = 13
const val maxBlues = 14

data class Game(
    var blues: Int,
    var reds: Int,
    var greens: Int,
)

fun calculateDay2a(input: List<String>): Int {
    val gameMap = input.associate { parseLine(it) }
    var result = 0

    for (set in gameMap.entries) {
        if (isGameValid(set.value)) {
            result += set.key;
        }
    }
    return result;
}

fun isGameValid(games: List<Game>): Boolean {
    for (game in games) {
        if (game.reds > maxReds) {
            return false
        }
        if (game.greens > maxGreens) {
            return false
        }
        if (game.blues > maxBlues) {
            return false
        }
    }
    return true
}

fun calculateDay2b(input: List<String>): Int {
    val gameMap = input.associate { parseLine(it) }
    var result = 0

    for (set in gameMap.entries) {
        val minReds = set.value.maxOf { it.reds }
        val minGreens = set.value.maxOf { it.greens }
        val minBlues = set.value.maxOf { it.blues }
        val power = minReds * minGreens * minBlues
        println("Power of Game ${set.value} is: $power")
        result += power;
    }
    return result
}


fun parseLine(line: String): Pair<Int, List<Game>> {
    val parts = line.split(":")
    val id = parts[0].removePrefix("Game ").toInt()
    val games = mutableListOf<Game>()
    val gameStrings = parts[1].split(";")
    for (gameString in gameStrings) {
        val game = Game(0, 0, 0)

        val colors = gameString.split(",")
        for (color in colors) {
            when {
                color.contains("red") -> game.reds = color.removeSuffix("red").trim().toInt()
                color.contains("blue") -> game.blues = color.removeSuffix("blue").trim().toInt()
                color.contains("green") -> game.greens = color.removeSuffix("green").trim().toInt()
            }
        }
        games.add(game)

    }
    return id to games
}
