package day4

import readInput

data class BoardStat(val winningNumber: Int, val score:Int, val board: Board)

/**
 * @author Team Nexus
 **/
fun boardStatWhichWin(gameInput: GameInput): MutableList<BoardStat> {
    val boardStats = mutableListOf<BoardStat>()
    val boardsLeft = gameInput.boards.toMutableSet()

    while(boardsLeft.isNotEmpty()) {
        val boardsLeftBefore = boardsLeft.toSet()

        for(i in gameInput.numbers.indices) {
            val game = Game(boardsLeft.toList())

            val currentNumber = gameInput.numbers[i]
            val winningBoards = game.playNumber(currentNumber)

            if (winningBoards.isNotEmpty()) {
                winningBoards.forEach { currentWinningBoard ->
                    boardStats.add(BoardStat(currentNumber, Game.calculateScoreForBoard(currentNumber, currentWinningBoard), currentWinningBoard))
                }

                boardsLeft.removeAll(winningBoards.toSet())
                break
            }
        }

        if (boardsLeftBefore == boardsLeft) {
            break
        }
    }

    return boardStats
}

fun main() {
    val gameInput = GameInput.fromLines(readInput("day4", object {}::class.java))
    val game = Game(gameInput.boards)

    println("${game.playToTillWinning(gameInput.numbers)}\n${game.getWinningNumber()}\n${game.getWinningBoard()}")

    val boardStats = boardStatWhichWin(gameInput)
    val lastBoard = boardStats.last()

    println("${lastBoard.score}\n${lastBoard.winningNumber}\n${lastBoard.board}")

}
