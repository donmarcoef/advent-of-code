import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Team Nexus
 */
internal class Day01KtTest {

    @Test
    fun testIncrease() {
        Assertions.assertEquals(7, nrOfIncreases(readInput("Day01_test", Day01KtTest::class.java)))
    }

}
