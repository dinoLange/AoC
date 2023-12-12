import kotlin.math.abs

fun calculateDay11a(input: List<String>): Long {
    val emptyRows = getEmptyRows(input)
    val emptyColumns = getEmptyColumns(input)
    val galaxies = getGalaxies(input)

    var returnSum = 0L
    for (fromGalaxy in galaxies) {
        for (toIndex in fromGalaxy.number + 1 until galaxies.size) {
            returnSum += shortestPath(fromGalaxy, galaxies[toIndex], emptyRows, emptyColumns, 1)
        }
    }
    return returnSum
}

fun calculateDay11b(input: List<String>): Long {
    val emptyRows = getEmptyRows(input)
    val emptyColumns = getEmptyColumns(input)
    val galaxies = getGalaxies(input)

    var returnSum = 0L
    for (fromGalaxy in galaxies) {
        for (toIndex in fromGalaxy.number + 1 until galaxies.size) {
            returnSum += shortestPath(fromGalaxy, galaxies[toIndex], emptyRows, emptyColumns, 999999L)
        }
    }
    return returnSum
}

fun getEmptyRows(input: List<String>): List<Int> {
    val emptyRows = mutableListOf<Int>()
    for (index in input.indices) {
        if (!input[index].contains('#')) {
            emptyRows.add(index)
        }
    }
    return emptyRows
}

fun getEmptyColumns(input: List<String>): List<Int> {
    val emptyColumns = mutableListOf<Int>()
    for (y in input[0].indices) {
        var containsGalaxy = false
        for (x in input.indices) {
            if (input[x][y] == '#') {
                containsGalaxy = true
            }
        }
        if (!containsGalaxy) {
            emptyColumns.add(y)
        }
    }
    return emptyColumns
}

fun getGalaxies(input: List<String>): List<Galaxy> {
    val galaxies = mutableListOf<Galaxy>()
    var countGalaxy = 0
    for (y in input.indices) {
        for (x in input[y].indices) {
            if (input[y][x] == '#') {
                galaxies.add(Galaxy(x, y, countGalaxy++))
            }
        }
    }
    return galaxies
}


fun shortestPath(
    fromGalaxy: Galaxy,
    toGalaxy: Galaxy,
    emptyRows: List<Int>,
    emptyColumns: List<Int>,
    expansionSpeed: Long,
): Long {
    val emptyRowsBetweenGalaxies =
        emptyRows.filter { it in minOf(fromGalaxy.y, toGalaxy.y)..maxOf(fromGalaxy.y, toGalaxy.y) }.size
    val emptyColumnsBetweenGalaxies =
        emptyColumns.filter { it in minOf(fromGalaxy.x, toGalaxy.x)..maxOf(fromGalaxy.x, toGalaxy.x) }.size
    return abs(fromGalaxy.x - toGalaxy.x) + abs(fromGalaxy.y - toGalaxy.y) + (emptyRowsBetweenGalaxies + emptyColumnsBetweenGalaxies) * expansionSpeed
}


data class Galaxy(
    val x: Int,
    val y: Int,
    val number: Int,

    )




