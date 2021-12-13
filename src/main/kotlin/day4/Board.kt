package day4

/**
 * @author Team Nexus
 **/
class Board {

    private var rows = listOf<MarkableNumbers>()

    private var columns = listOf<MarkableNumbers>()

    private var markedNumbers = mutableSetOf<Int>()

    private var lines = mutableListOf<List<Int>>()

    private var nrPositions = mutableMapOf<Int, Pair<Int, Int>>()

    private var isComplete:Boolean=false

    fun markNumber(number: Int): Boolean {
        val pos = nrPositions[number]

        if (pos == null) {
            return false
        } else {
            val lineComplete = rows[pos.first].markNumber(number)
            val columnComplete = columns[pos.second].markNumber(number)

            markedNumbers.add(number)

            this.isComplete = lineComplete || columnComplete

            return this.isComplete
        }
    }

    fun isComplete() = isComplete

    fun numbers(): List<Int> = lines.flatten()

    fun unmarkedNumbers() = nrPositions.keys.subtract(markedNumbers)

    companion object {

        fun readFromText(lines: List<String>): Board {
            return ofInts(lines.map {
                it.split(" ").mapNotNull { it.trim() }.filter { it.isNotEmpty() }.mapNotNull { it.toInt() }
            })
        }

        fun ofInts(numbers: List<List<Int>>): Board {
            if (numbers.size != 5)
                throw IllegalStateException("no exact 5 lines (${numbers.size})")

            val board = Board()

            val columns = mutableListOf<MutableSet<Int>>()
            val rows = mutableListOf<MutableSet<Int>>()

            numbers.forEachIndexed { lineNr, line ->
                board.lines.add(line)

                line.forEachIndexed { columnNr, number ->
                    getOrAdd(number, columnNr, columns)
                    getOrAdd(number, lineNr, rows)

                    board.nrPositions[number] = Pair(lineNr, columnNr)
                }
            }

            board.rows = rows.map { MarkableNumbers(it) }
            board.columns = columns.map { MarkableNumbers(it) }

            return board
        }

        private fun getOrAdd(number: Int, pos: Int, list: MutableList<MutableSet<Int>>) {
            list.getOrElse(pos) {
                val newSet = mutableSetOf<Int>()
                list.add(newSet)
                newSet
            }.add(number)
        }
    }

    private data class MarkableNumbers(val numbers: Set<Int>) {
        private val markedNumbers = mutableSetOf<Int>()

        fun markNumber(number: Int): Boolean {
            if (numbers.contains(number) && !markedNumbers.contains(number)) {
                markedNumbers.add(number)

                return markedNumbers.size == numbers.size
            }

            return false
        }
    }

    override fun toString(): String {
        return this.lines.map {
            line -> line.map { it }.joinToString(" ")
        }.joinToString("\n")
    }
}

fun main() {
    val board = Board.readFromText(
        """22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19""".splitToSequence('\n', '\r').toList()
    )

    println("test")
}
