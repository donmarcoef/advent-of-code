fun nrOfIncreases(input: List<Int>): Int {
    var previousValue: Int? = null
    var nrOfIncreases = 0

    input.forEach { current ->
        previousValue?.let {
            if (current > it) {
                nrOfIncreases++
            }
        }

        previousValue = current
    }

    return nrOfIncreases
}

fun nrOfIncreasesFromSlices(input: List<List<Int>>) =
    nrOfIncreases(input.map {
        it.sum()
    })

fun <T> slice(input: List<T>, sliceCount: Int): List<List<T>> {
    var completeList = mutableListOf<List<T>>()
    var currentSlice = mutableListOf<T>()

    val iterator = input.iterator()
    while (true) {
        if (currentSlice.isNotEmpty()) {
            if (currentSlice.size >= sliceCount) {
                completeList.add(currentSlice.slice(IntRange(0, sliceCount - 1)))

                currentSlice.removeFirstOrNull()
            } else {
                break
            }
        }

        for (i in 1..sliceCount) {
            if (iterator.hasNext()) {
                currentSlice.add(iterator.next())
            } else {
                break
            }
        }
    }

    return completeList
}

fun main() {
    val input = readInput("Day01").map { it.toInt() }
    val slicedInput = slice(input, 3)

    println(nrOfIncreasesFromSlices(slicedInput))
}
