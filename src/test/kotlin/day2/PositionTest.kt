package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import readInput

/**
 * @author Team Nexus
 */
internal class PositionTest {

    @Test
    fun testPositionChanging() {
        val position = Position().forward(5).down(5).forward(8).up(3).down(8).forward(2)

        assertEquals(15, position.horizontalPosition)
        assertEquals(10, position.depth)
    }

    @Test
    fun testActionFromText() {
        val position = Position()

        readInput("actions", PositionTest::class.java).map {
            ActionFactory.forText(it)
        }.map {
            position.runActions(it)
        }

        assertEquals(15, position.horizontalPosition)
        assertEquals(10, position.depth)
    }
}
