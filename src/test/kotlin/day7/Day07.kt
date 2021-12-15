package day7

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Team Nexus
 **/
class Day07 {

    @Test
    fun testCrabReordering() {
        Assertions.assertEquals(37, reorderingCrabs(listOf(16,1,2,0,4,2,7,1,2,14).map { Crab(it) }))
    }

}
