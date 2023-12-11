import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day10KtTest {

    @Test
    fun calculateDay10a() {
        val input = File("inputs/day10.txt").readLines()
        assertEquals(6640, calculateDay10a(input))
    }

}