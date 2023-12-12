import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.math.absoluteValue

class Day11KtTest {

    @Test
    fun calculateDay11a() {
        val input = File("inputs/day11.txt").readLines()
        assertEquals(9795148, calculateDay11a(input))
    }

    @Test
    fun calculateDay11b() {
        val input = File("inputs/day11.txt").readLines()
        assertEquals(650672493820, calculateDay11b(input))
    }

}