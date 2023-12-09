fun calculateDay8b(input: List<String>): Long {
    val instructions = input[0]

    val nodes = input.filter { it.contains("=") }.map {
        Node(
            it.split(" = ")[0],
            it.split(" = ")[1].split(',')[0].trim().removePrefix("("),
            it.split(" = ")[1].split(',')[1].trim().removeSuffix(")")
        )
    }
    val nodeMap = nodes.associateBy { it.node }
    val startNodes = nodeMap.values.filter { it.node.contains("A") }
    val endNodes = nodeMap.values.filter { it.node.contains("Z") }
    val steps = mutableListOf<Long>()
    for (startNode in startNodes) {
        steps.add(findPath(instructions, nodeMap, startNode, endNodes).toLong())
    }

    return lcmOfList(steps)
}

fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

fun lcm(a: Long, b: Long): Long {
    return if (a == 0L || b == 0L) 0 else (a * b) / gcd(a, b)
}

fun lcmOfList(numbers: List<Long>): Long {
    return numbers.reduce { acc, num -> lcm(acc, num) }
}
fun calculateDay8a(input: List<String>): Int {
    val instructions = input[0]

    val nodes = input.filter { it.contains("=") }.map {
        Node(
            it.split(" = ")[0],
            it.split(" = ")[1].split(',')[0].trim().removePrefix("("),
            it.split(" = ")[1].split(',')[1].trim().removeSuffix(")")
        )
    }
    val nodeMap = nodes.associateBy { it.node }
    return findPath(instructions, nodeMap, nodeMap["AAA"]!!, listOf(nodeMap["ZZZ"]!!))
}

fun findPath(instructions: String, nodeMap: Map<String, Node>, startNode: Node, endNodes: List<Node>): Int {
    var instructionPointer = 0
    var stepCounter = 0
    var currentNode = startNode

    while (true) {
        if (instructions[instructionPointer] == 'R') {
            currentNode = nodeMap[currentNode.right]!!
        }
        if (instructions[instructionPointer] == 'L') {
            currentNode = nodeMap[currentNode.left]!!
        }
        stepCounter++
        instructionPointer++
        if (instructionPointer >= instructions.length) {
            instructionPointer = 0
        }
        if (endNodes.contains(currentNode)) {
            return stepCounter
        }
    }
}


data class Node(
    val node: String,
    val left: String,
    val right: String
)