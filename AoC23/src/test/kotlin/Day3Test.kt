import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day3Test {

    @Test
    fun calculateDay3a() {
        val inputDay1 = File("inputs/day3.txt").readLines();
        println(calculateDay3a(inputDay1))
        kotlin.test.assertEquals(528799, calculateDay3a(inputDay1))
    }

    @Test
    fun calculateDay3b() {
        val inputDay1 = File("inputs/day3.txt").readLines();
        println(calculateDay3b(inputDay1))
        kotlin.test.assertEquals(84907174, calculateDay3b(inputDay1))
    }
}