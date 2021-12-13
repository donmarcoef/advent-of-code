package day4

/**
 * @author Team Nexus
 **/
data class GameInput(val numbers: List<Int>, val boards: List<Board>) {

    companion object {

        fun fromLines(lines: List<String>): GameInput {
            if (lines.size < 2) {
                throw IllegalArgumentException("no valid game input")
            }

            var numbersToPlay = lines[0].split(",").mapNotNull { it.trim() }.map { it.toInt() }

            var linesToProcess = mutableListOf<String>()
            var boards = mutableListOf<Board>()

            var addBoard = {
                if (linesToProcess.isNotEmpty()) {
                    val board = Board.ofInts(linesToProcess.map {
                        it.split(" ")
                            .mapNotNull { it.trim() }
                            .filter { it.isNotBlank() }
                            .map { it.toInt() }
                            .toList()
                    })
                    boards.add(board)
                }
            }

            for (i in IntRange(2, lines.size-1)) {
                val line = lines[i]

                if (line.isEmpty()) {
                    addBoard()
                    linesToProcess.clear()
                } else {
                    linesToProcess.add(line)
                }
            }

            addBoard()

            return GameInput(numbersToPlay, boards)
        }

    }

}
