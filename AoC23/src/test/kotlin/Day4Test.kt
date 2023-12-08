import org.junit.jupiter.api.Test

import java.io.File

class Day4Test {

    @Test
    fun testCalculateDay4a() {
        val input = File("inputs/day4.txt").readLines();
        kotlin.test.assertEquals(26443, calculateDay4a(input))
    }

    @Test
    fun testCalculateDay4b() {
        val input = File("inputs/day4.txt").readLines();
        kotlin.test.assertEquals(6284877, calculateDay4b(input))
    }


}