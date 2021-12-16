package day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import readInput

/**
 * @author Team Nexus
 **/
class Day9Test {

    @Test
    fun testUp() {
        Assertions.assertEquals(Position(1, 4), Position(1,5).up())
    }

    @Test
    fun testDown() {
        Assertions.assertEquals(Position(1, 6), Position(1,5).down())
    }

    @Test
    fun testLeft() {
        Assertions.assertEquals(Position(0, 5), Position(1,5).left())
    }

    @Test
    fun testRight() {
        Assertions.assertEquals(Position(2, 5), Position(1,5).right())
    }

    @Test
    fun testNeighbour() {
        Assertions.assertFalse(LocationHeight(Position(0, 0), 2, null, 3, null, 1).isLowerAsNeighbours())
        Assertions.assertTrue(LocationHeight(Position(1, 0), 1, null, 9, 2, 9).isLowerAsNeighbours())
    }

    @Test
    fun testLowestLocationHeights() {
        val locationHeights = readLocationHeights(readInput("heights", Day9Test::class.java, true))
        Assertions.assertEquals(listOf(1,0,5,5), lowestLocationHeights(locationHeights))
    }

    @Test
    fun riskLevelTest() {
        Assertions.assertEquals(15, riskLevel(listOf(1,0,5,5)))
    }
}
