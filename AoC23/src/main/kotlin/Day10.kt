import javax.sound.sampled.LineEvent
import kotlin.math.floor

fun calculateDay10a(input: List<String>): Int {
    val pipeMatrix: Array<Array<Coordinate>> = Array(input.size) { Array(input[0].length) { Coordinate() } }
    val start = Coordinate(0, 0, PipeType.START)
    for (y in input.indices) {
        for (x in input[y].indices) {
            val type = getPipeTypeByChar(input[y][x])
            pipeMatrix[x][y] = Coordinate(x, y, type)
            if (type == PipeType.START) {
                start.x = x
                start.y = y
            }
        }
    }
    findLoop(start, Coordinate(0, 0, PipeType.NONE), pipeMatrix)
    return globalCounter / 2 //floored
}

var globalCounter = 0

fun findLoop(start: Coordinate, last: Coordinate, pipeMatrix: Array<Array<Coordinate>>) {
    println(start.type)
    globalCounter++
    val validPipeNeighbors: List<Coordinate> = findValidPipeNeighbors(start, last, pipeMatrix)
    if (validPipeNeighbors.size != 2) {
        throw IllegalArgumentException()
    }
    if (last.type != PipeType.NONE && start.type == PipeType.START) {
        return
    }
    if (validPipeNeighbors[0].x == last.x && validPipeNeighbors[0].y == last.y) {
        findLoop(validPipeNeighbors[1], start, pipeMatrix)
    } else {
        findLoop(validPipeNeighbors[0], start, pipeMatrix)
    }
}

fun findValidPipeNeighbors(
    start: Coordinate,
    last: Coordinate,
    pipeMatrix: Array<Array<Coordinate>>
): List<Coordinate> {

    //QUATSCH:
    val pipeNeighbors = mutableListOf<Coordinate>()
    if (start.x < pipeMatrix.size - 1) {
        val rightNeighbor = pipeMatrix[start.x + 1][start.y]
        if (rightNeighbor.type.directions.contains(Direction.LEFT)) {
            pipeNeighbors.add(rightNeighbor)
        }
    }
    if (start.y < pipeMatrix.size - 1) {
        val bottomNeighbor = pipeMatrix[start.x][start.y + 1]
        if (bottomNeighbor.type.directions.contains(Direction.TOP)) {
            pipeNeighbors.add(bottomNeighbor)
        }
    }
    if (start.x > 0) {
        val leftNeighbor = pipeMatrix[start.x - 1][start.y]
        if (leftNeighbor.type.directions.contains(Direction.RIGHT)) {
            pipeNeighbors.add(leftNeighbor)
        }
    }
    if (start.y > 0) {
        val topNeighbor = pipeMatrix[start.x][start.y - 1]
        if (topNeighbor.type.directions.contains(Direction.BOTTOM)) {
            pipeNeighbors.add(topNeighbor)
        }
    }
    return pipeNeighbors
}

data class Coordinate(
    var x: Int = 0,
    var y: Int = 0,
    var type: PipeType = PipeType.GROUND,
)

enum class PipeType(val directions: List<Direction>) {
    VERTICAL(listOf(Direction.TOP, Direction.BOTTOM)),
    HORIZONTAL(listOf(Direction.LEFT, Direction.RIGHT)),
    NORTH_EAST(listOf(Direction.TOP, Direction.RIGHT)),
    NORTH_WEST(listOf(Direction.TOP, Direction.LEFT)),
    SOUTH_WEST(listOf(Direction.BOTTOM, Direction.LEFT)),
    SOUTH_EAST(listOf(Direction.BOTTOM, Direction.RIGHT)),
    GROUND(listOf(Direction.NONE, Direction.NONE)),
    NONE(listOf(Direction.NONE, Direction.NONE)),
    START(listOf(Direction.LEFT, Direction.RIGHT, Direction.TOP, Direction.BOTTOM));
}

enum class Direction {
    LEFT, RIGHT, TOP, BOTTOM, NONE
}

fun getPipeTypeByChar(c: Char): PipeType {
    return when (c) {
        '|' -> PipeType.VERTICAL
        '-' -> PipeType.HORIZONTAL
        'L' -> PipeType.NORTH_EAST
        'J' -> PipeType.NORTH_WEST
        '7' -> PipeType.SOUTH_WEST
        'F' -> PipeType.SOUTH_EAST
        'S' -> PipeType.START
        else -> PipeType.GROUND
    }
}

fun calculateDay10b(input: List<String>) {

}

