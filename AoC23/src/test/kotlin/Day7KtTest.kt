import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day7KtTest {

    @Test
    fun calculateDay7a() {
        val input = File("inputs/day7.txt").readLines()
        assertEquals(250474325, calculateDay7a(input))
    }

}