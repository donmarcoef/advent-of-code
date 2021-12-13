package day4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import readInput

/**
 * @author Team Nexus
 **/
class Day4Test {

    @Test
    fun testBoard() {
        val board = Board.ofInts(
            listOf(
                listOf(14, 21, 17, 24, 4),
                listOf(10, 16, 15, 9, 19),
                listOf(18, 8, 23, 26, 20),
                listOf(22, 11, 13, 6, 5),
                listOf(2, 0, 12, 3, 7)
            )
        )
        val firstRound = listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21)
        firstRound.forEach {
            board.markNumber(it)
        }
        Assertions.assertEquals(false, board.isComplete())
        board.markNumber(24)
        Assertions.assertEquals(true, board.isComplete())

        Assertions.assertEquals(188, board.unmarkedNumbers().sum())
    }

    @Test
    fun testBoardColumnFinish() {
        val board = Board.ofInts(
            listOf(
                listOf(14, 21, 17, 24, 4),
                listOf(10, 16, 15, 9, 19),
                listOf(18, 8, 23, 26, 20),
                listOf(22, 11, 13, 6, 5),
                listOf(2, 0, 12, 3, 7)
            )
        )
        listOf(14,10,18,22).forEach {
            board.markNumber(it)
        }
        Assertions.assertFalse(board.isComplete())
        board.markNumber(2)
        Assertions.assertTrue(board.isComplete())
    }

    @Test
    fun testGameInput() {
        val gameInput = GameInput.fromLines(readInput("gameInput", Day4Test::class.java))

        Assertions.assertEquals(
            listOf(
                7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1
            ), gameInput.numbers
        )
        Assertions.assertEquals(3, gameInput.boards.size)
        Assertions.assertEquals(
            listOf(
                22, 13, 17, 11, 0, 8, 2, 23, 4, 24, 21, 9, 14, 16, 7, 6, 10, 3, 18, 5, 1, 12, 20, 15, 19
            ), gameInput.boards[0].numbers()
        )
        Assertions.assertEquals(
            listOf(
                3, 15, 0, 2, 22, 9, 18, 13, 17, 5, 19, 8, 7, 25, 23, 20, 11, 10, 24, 4, 14, 21, 16, 12, 6
            ), gameInput.boards[1].numbers()
        )
        Assertions.assertEquals(
            listOf(
                14, 21, 17, 24, 4, 10, 16, 15, 9, 19, 18, 8, 23, 26, 20, 22, 11, 13, 6, 5, 2, 0, 12, 3, 7
            ), gameInput.boards[2].numbers()
        )

        val game = Game(gameInput.boards)
        Assertions.assertEquals(4512, game.playToTillWinning(gameInput.numbers))
    }
}
