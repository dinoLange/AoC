import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day8KtTest {

    @Test
    fun calculateDay8() {
        val input = File("inputs/day8.txt").readLines()
        assertEquals(23147, calculateDay8a(input))
    }

    @Test
    fun calculateDay8b() {
        val input = File("inputs/day8.txt").readLines()
        assertEquals(22289513667691, calculateDay8b(input))
    }
}