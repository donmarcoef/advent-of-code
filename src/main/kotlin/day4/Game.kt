package day4

/**
 * @author Team Nexus
 **/
class Game(val boards: List<Board>) {

    private var winningNumber:Int = -1
    private var winningBoard:Board?=null

    fun playNumber(number: Int): List<Board> {
        var winningBoards = mutableListOf<Board>()

        this.boards.forEach { board ->
            if (board.markNumber(number)) {
                winningBoards.add(board)
            }
        }

        return winningBoards
    }

    fun playToTillWinning(numbers:List<Int>): Int {
        for (i in numbers.indices) {
            var currentNumber = numbers[i]
            val winningBoards = playNumber(currentNumber)

            if (winningBoards.isNotEmpty()) {
                if (winningBoards.size > 1) {
                    throw IllegalStateException("more then one board wins")
                } else {
                    this.winningBoard = winningBoards.first()
                    this.winningNumber = currentNumber

                    return this.winningBoard!!.unmarkedNumbers().sum() * currentNumber
                }
            }
        }

        throw IllegalStateException("no board wins")
    }

    fun getWinningNumber() = winningNumber

    fun getWinningBoard() = winningBoard?:throw IllegalArgumentException("no board wins")
}

fun main() {
//    Game().readFromText(readInput("day4", Game::class.java, false))
}
