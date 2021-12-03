package day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import readInput

/**
 * @author Team Nexus
 **/
class Day3Test {

    @Test
    fun testBinaryNumbers() {
        val binaryNumbers = readInput("binaryNumbers", Day3Test::class.java)
        Assertions.assertEquals(198, binaryDiagnostic(binaryNumbers))
    }
}
