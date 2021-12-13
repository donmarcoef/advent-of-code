package day4

import readInput

/**
 * @author Team Nexus
 **/
fun main() {
    val gameInput = GameInput.fromLines(readInput("day4", object {}::class.java))
    val game = Game(gameInput.boards)

    println("${game.playToTillWinning(gameInput.numbers)}\n${game.getWinningNumber()}\n${game.getWinningBoard()}")
}
