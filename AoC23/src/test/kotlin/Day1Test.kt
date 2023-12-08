import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun calculateDay1a() {
        val inputDay1 = File("inputs/day1.txt").readLines();
        assertEquals(55172, calculateDay1a(inputDay1))
    }

    @Test
    fun calculateDay1b() {
        val inputDay1 = File("inputs/day1.txt").readLines();
        assertEquals(54925, calculateDay1b(inputDay1))
    }
}