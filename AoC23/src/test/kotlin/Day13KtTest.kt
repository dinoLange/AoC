import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day13KtTest {

    @Test
    fun calculateDay13() {
        val input = File("inputs/sample.txt").readLines()
        assertEquals(1, calculateDay13a(input))
    }
}