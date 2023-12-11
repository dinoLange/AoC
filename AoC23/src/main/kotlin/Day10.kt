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
    return findLoop(start, pipeMatrix)
}


fun findLoop(start: Coordinate, pipeMatrix: Array<Array<Coordinate>>): Int {
    val validPipeNeighbors: List<Direction> = findStartPipes(start, pipeMatrix)
    if (validPipeNeighbors.size != 2) {
        throw IllegalArgumentException()
    }
    var path = mutableListOf<Coordinate>()
    var direction = validPipeNeighbors[0]
    var current = start
    var count = 0
    while (current.type != PipeType.START || count == 0) {
        val next = goToNextPipe(direction, current, pipeMatrix)
        path.add(next)
        current = next
        direction = getNextDirection(next, direction)
        count++
    }
    return count / 2
}


fun getNextDirection(next: Coordinate, fromDirection: Direction): Direction {
    val opposite = getOppositeDirection(fromDirection)
    return if (next.type.directions.first == opposite)
        next.type.directions.second
    else
        next.type.directions.first

}

fun getOppositeDirection(direction: Direction): Direction {
    return when (direction) {
        Direction.TOP -> Direction.BOTTOM
        Direction.BOTTOM -> Direction.TOP
        Direction.LEFT -> Direction.RIGHT
        Direction.RIGHT -> Direction.LEFT
        else -> Direction.NONE
    }
}

fun goToNextPipe(direction: Direction, current: Coordinate, pipeMatrix: Array<Array<Coordinate>>): Coordinate {
    if (direction == Direction.TOP) {
        return pipeMatrix[current.x][current.y - 1]
    }
    if (direction == Direction.RIGHT) {
        return pipeMatrix[current.x + 1][current.y]
    }
    if (direction == Direction.BOTTOM) {
        return pipeMatrix[current.x][current.y + 1]
    }
    if (direction == Direction.LEFT) {
        return pipeMatrix[current.x - 1][current.y]
    }
    throw IllegalArgumentException("Input not valid")

}

fun findStartPipes(start: Coordinate, pipeMatrix: Array<Array<Coordinate>>): List<Direction> {
    val pipeNeighbors = mutableListOf<Direction>()
    if (start.x < pipeMatrix.size - 1) {
        val rightNeighbor = pipeMatrix[start.x + 1][start.y]
        if (rightNeighbor.type.directions.toList().contains(Direction.LEFT)) {
            pipeNeighbors.add(Direction.RIGHT)
        }
    }
    if (start.y < pipeMatrix.size - 1) {
        val bottomNeighbor = pipeMatrix[start.x][start.y + 1]
        if (bottomNeighbor.type.directions.toList().contains(Direction.TOP)) {
            pipeNeighbors.add(Direction.BOTTOM)
        }
    }
    if (start.x > 0) {
        val leftNeighbor = pipeMatrix[start.x - 1][start.y]
        if (leftNeighbor.type.directions.toList().contains(Direction.RIGHT)) {
            pipeNeighbors.add(Direction.LEFT)
        }
    }
    if (start.y > 0) {
        val topNeighbor = pipeMatrix[start.x][start.y - 1]
        if (topNeighbor.type.directions.toList().contains(Direction.BOTTOM)) {
            pipeNeighbors.add(Direction.TOP)
        }
    }
    return pipeNeighbors
}


data class Coordinate(
    var x: Int = 0,
    var y: Int = 0,
    var type: PipeType = PipeType.GROUND,
)

enum class PipeType(val directions: Pair<Direction, Direction>, val char: Char) {
    VERTICAL(Direction.TOP to Direction.BOTTOM, '|'),
    HORIZONTAL(Direction.LEFT to Direction.RIGHT, '-'),
    NORTH_EAST(Direction.TOP to Direction.RIGHT, 'L'),
    NORTH_WEST(Direction.TOP to Direction.LEFT, 'J'),
    SOUTH_WEST(Direction.BOTTOM to Direction.LEFT, '7'),
    SOUTH_EAST(Direction.BOTTOM to Direction.RIGHT, 'F'),
    GROUND(Direction.NONE to Direction.NONE, '.'),
    NONE(Direction.NONE to Direction.NONE, '.'),
    START(Direction.NONE to Direction.NONE, 'S');
}

enum class Direction() {
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

