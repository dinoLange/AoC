fun calculateDay6a(input: List<String>): Int {
    val games = mutableListOf<BoatGame>()
    val times = input[0].split("\\s+".toRegex())
    val distances = input[1].split("\\s+".toRegex())

    for (index in 1 until times.size) {
        games.add(BoatGame(times[index].toLong(), distances[index].toLong()))
    }
    return games.map { waysToBeatTheRecord(it) }.reduce { a, b -> a * b }
}

fun waysToBeatTheRecord(game: BoatGame): Int {
    var waysToBeatRecord = 0
    for (speed in 1 until game.time) {
        val remainingTime = game.time - speed
        val distanceCovered = remainingTime * speed
        if (distanceCovered > game.distance) {
            waysToBeatRecord++
        }
    }
    return waysToBeatRecord
}


fun calculateDay6b(input: List<String>): Int {
    val time = input[0].filter { it.isDigit() }.toLong()
    val distance = input[1].filter { it.isDigit() }.toLong()
    return waysToBeatTheRecord(BoatGame(time, distance))
}

data class BoatGame(
    val time: Long,
    val distance: Long,
)