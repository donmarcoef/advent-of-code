package day6

import readInput

/**
 * @author Team Nexus
 **/
class LaternFish(initialState: Int) {
    private var state = initialState

    fun newDay(): LaternFish? {
        if (state == 0) {
            state = 6

            return LaternFish(8)
        }

        state--

        return null
    }

    fun getState() = state

}

fun startSimulation(fishStates: List<Int>, nrOfDays: Int): Int {
    val listFishes = fishStates.map { LaternFish(it) }.toMutableList()

    for (i in IntRange(1, nrOfDays)) {
        val newFishes = mutableListOf<LaternFish>()

        listFishes.forEach {
            val newFish = it.newDay()

            if (newFish != null) {
                newFishes.add(newFish)
            }
        }

        listFishes.addAll(newFishes)
    }

    return listFishes.size
}

fun main() {
    val fishes = readInput("day6", object {}::class.java).first().split(",").map { it.toInt() }
    println(startSimulation(fishes, 80))
}
