fun nrOfIncreases(input: List<String>): Int {
    var previousValue: Int? = null
    var nrOfIncreases = 0

    input.map { it.toInt() }.forEach { current ->
        previousValue?.let {
            if (current > it) {
                nrOfIncreases++
            }
        }

        previousValue = current
    }

    return nrOfIncreases
}

fun main() {
    println(nrOfIncreases(readInput("Day01")))
}
