import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Team Nexus
 */
internal class Day01KtTest {

    @Test
    fun testIncrease() {
        Assertions.assertEquals(7, nrOfIncreases(readTestData().map { it.toInt() }))
    }

    @Test
    fun slice() {
        val listOfSlices = slice(readTestData(), 3)
        Assertions.assertEquals(8, listOfSlices.size)

        val expectedList = listOf(
            listOf("199", "200", "208"),
            listOf("200", "208", "210"),
            listOf("208", "210", "200"),
            listOf("210", "200", "207"),
            listOf("200", "207", "240"),
            listOf("207", "240", "269"),
            listOf("240", "269", "260"),
            listOf("269", "260", "263")
        )

        Assertions.assertEquals(expectedList, listOfSlices)
    }

    @Test
    fun nrOfIncreasesFromSlices() {
        val slicedList = slice(readTestData(), 3).map {
            it.map {
                it.toInt()
            }
        }
        Assertions.assertEquals(5, nrOfIncreasesFromSlices(slicedList))
    }

    private fun readTestData() = readInput("Day01_test", Day01KtTest::class.java)
}
