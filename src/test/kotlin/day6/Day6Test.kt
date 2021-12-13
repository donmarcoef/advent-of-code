package day6

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Team Nexus
 **/
class Day6Test {

    @Test
    fun testSimulation() {
        Assertions.assertEquals(26,  startSimulation(listOf(3, 4, 3, 1, 2), 18))
        Assertions.assertEquals(5934, startSimulation(listOf(3, 4, 3, 1, 2), 80))
    }

}
