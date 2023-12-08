import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day6KtTest {

    @Test
    fun calculateDay6a() {
        val input = File("inputs/day6.txt").readLines()
        assertEquals(114400, calculateDay6a(input))
    }

    @Test
    fun calculateDay6b() {
        val input = File("inputs/day6.txt").readLines()
        assertEquals(21039729, calculateDay6b(input))
    }

}