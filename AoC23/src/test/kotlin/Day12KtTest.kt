import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day12KtTest {

    @Test
    fun calculateDay12a() {
        val input = File("inputs/sample.txt").readLines()
        assertEquals(1, calculateDay12a(input))
    }
}