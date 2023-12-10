import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day9KtTest {

    @Test
    fun calculateDay9() {

        val input = File("inputs/day9.txt").readLines()
        assertEquals(1581679977, calculateDay9a(input))
    }

    @Test
    fun calculateDay9b() {

        val input = File("inputs/day9.txt").readLines()
        assertEquals(889, calculateDay9b(input))
    }
}