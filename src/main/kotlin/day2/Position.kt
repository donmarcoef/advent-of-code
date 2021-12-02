package day2

/**
 * @author Team Nexus
 **/
class Position {

    private var currentHorizontalPosition: Int = 0

    private var currentDepth: Int = 0

    val horizontalPosition
        get() = currentHorizontalPosition

    val depth
        get() = currentDepth

    fun forward(nr: Int): Position {
        currentHorizontalPosition += nr

        return this
    }

    fun down(paraDepth: Int): Position {
        currentDepth += paraDepth

        return this
    }

    fun up(paraDepth: Int): Position {
        down(paraDepth * -1)

        return this
    }

    fun runActions(vararg actions:Action) {
        actions.forEach { currentAction ->
            currentAction.doWithPosition(this)
        }
    }
}

interface Action {
    fun doWithPosition(position: Position)
}

object ActionFactory {
    fun forText(text: String): Action {
        val actionWithCnt = text.split(" ")

        if (actionWithCnt.size != 2) {
            throw IllegalArgumentException("can't create action for text '$text")
        }

        val action = actionWithCnt.first()
        val cnt = try {
            actionWithCnt.last().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("cnt for action '$text' isn't a number")
        }

        return when (action) {
            "forward" -> {
                ForwardAction(cnt)
            }
            "down" -> {
                DownAction(cnt)
            }
            "up" -> {
                UpAction(cnt)
            }
            else -> {
                throw IllegalArgumentException("unknown action '$action'")
            }
        }
    }
}

class ForwardAction(private val cnt: Int) : Action {
    override fun doWithPosition(position: Position) {
        position.forward(cnt)
    }
}

class DownAction(private val cnt: Int) : Action {
    override fun doWithPosition(position: Position) {
        position.down(cnt)
    }
}

class UpAction(private val cnt: Int) : Action {
    override fun doWithPosition(position: Position) {
        position.up(cnt)
    }
}
