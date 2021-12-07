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

    @Test
    fun oxygenGenerator() {
        val binaryNumbers = readInput("binaryNumbers", Day3Test::class.java)

        val oxygenGenerator = lifeSupportingRate(binaryNumbers, oxygenGeneratorRating())
        Assertions.assertEquals(23, oxygenGenerator)
    }

    @Test
    fun co2Generator() {
        val binaryNumbers = readInput("binaryNumbers", Day3Test::class.java)

        val co2ScrubberGenerator = lifeSupportingRate(binaryNumbers, co2ScrubberRating())
        Assertions.assertEquals(10, co2ScrubberGenerator)
    }
}
