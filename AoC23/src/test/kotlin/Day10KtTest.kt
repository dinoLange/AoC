import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day10KtTest {

    @Test
    fun calculateDay10a() {
        val input = File("inputs/day10.txt").readLines()
        assertEquals(889, calculateDay10a(input))
    }

    @Test
    fun calculateDay10b() {
        val input = File("inputs/day10.txt").readLines()
        assertEquals(889, calculateDay10b(input))
    }
}