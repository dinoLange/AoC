import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class Day2Test {

    @Test
    fun testDay2a() {
        val inputDay2 = File("inputs/day2.txt").readLines();
        assertEquals(2164, calculateDay2a(inputDay2))

    }

    @Test
    fun testDay2b() {
        val inputDay2 = File("inputs/day2.txt").readLines();
        assertEquals(69929, calculateDay2b(inputDay2))

    }
}