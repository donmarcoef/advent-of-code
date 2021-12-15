package day7

import readInput

/**
 * @author Team Nexus
 **/
data class Crab(var position:Int) {

    fun neededFuelToReachPosition(position: Int): Int {
        val steps = if (this.position <= position) {
            position - this.position
        } else {
            this.position - position
        }

        var sum = 0
        for(i in IntRange(1, steps)) {
            sum+=i
        }

        return sum
    }
}

fun reorderingCrabs(crabs:List<Crab>): Int {
    val sortedCrabs = crabs.sortedBy { it.position }
    val minimumPos = sortedCrabs.first().position
    val maximumPos = sortedCrabs.last().position

    var calulateFuel = { newPos:Int ->
        sortedCrabs.sumOf {
            var neededFuel = it.neededFuelToReachPosition(newPos)

            neededFuel
        }
    }

    val neededFuel = mutableMapOf<Int, Int>()
    for(i in IntRange(minimumPos, maximumPos)) {
        neededFuel.put(i, calulateFuel(i))
    }

    return neededFuel.values.minOf { it }
}

fun main() {
    val positions = readInput("crabPositions", object {}::class.java)[0].split(",").map { it.toInt() }.map { Crab(it) }

    println(reorderingCrabs(positions))
}
