package day9

import readInput

/**
 * @author Team Nexus
 **/
data class LocationHeight(
    val position: Position, val height: Int,
    val up: Int?,
    val down: Int?,
    val left: Int?,
    val right: Int?
) {

    fun isLowerAsNeighbours(): Boolean {
        val isLower = { toCheckHeight: Int? ->
            if (toCheckHeight != null)  height < toCheckHeight else true
        }

        return isLower(up) && isLower(down) && isLower(left) && isLower(right)
    }

}

data class Position(
    val x: Int,
    val y: Int,
) {

    fun up() = Position(x, y - 1)

    fun down() = Position( x, y + 1)

    fun left() = Position(x - 1, y)

    fun right() = Position(x + 1, y)

}

fun locationHeights(heights: List<List<Int>>): MutableMap<Position, LocationHeight> {
    val maxY = heights.size
    val maxX = heights.first().size

    val getElementOrNull = { position: Position ->
        if (position.x >= maxX || position.x < 0 || position.y < 0 || position.y >= maxY) {
            null
        } else {
            heights[position.y][position.x]
        }
    }

    val values = mutableMapOf<Position, LocationHeight>()
    for (y in IntRange(0, maxY - 1)) {
        for (x in IntRange(0, maxX - 1)) {
            val position = Position(x, y)
            val currentHeight = heights[y][x]

            values[position] = LocationHeight(
                position,
                currentHeight,
                getElementOrNull(position.up()),
                getElementOrNull(position.down()),
                getElementOrNull(position.left()),
                getElementOrNull(position.right())
            )
        }
    }

    return values
}

fun readLocationHeights(values: List<String>) =
    locationHeights(values.map { line ->
        line.map { it.digitToInt() }
    })

fun lowestLocationHeights(locationHeights: MutableMap<Position, LocationHeight>) =
    locationHeights.values.filter { it.isLowerAsNeighbours() }.mapNotNull { it.height }

fun riskLevel(lowestValues:List<Int>) = lowestValues.sumOf { it + 1 }

fun main() {

    val locationHeights = readLocationHeights(readInput("heights", LocationHeight::class.java, true))
    val lowestValues = lowestLocationHeights(locationHeights)

    println(riskLevel(lowestValues))
}
