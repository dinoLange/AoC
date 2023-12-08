import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day5KtTest {

    @Test
    fun calculateDay5a() {
        val input = File("inputs/day5.txt").readLines();
        assertEquals(227653707, calculateDay5a(input))
    }

    @Test
    fun testCorrespondingNumber() {
        val input =
            "seed-to-soil map:\n" +
            "50 98 2\n" +
            "52 50 48\n"
        val maps = parseCategoryMaps(input.split("\n"))

        for (i in longArrayOf(79, 14, 55, 13)) {
            println("Number $i has corresponding number ${getCorrespondingNumber(i, maps[0].second.ranges)}")
        }
    }
}