package day2

/**
 * @author Team Nexus
 **/
class Position {

    private var currentHorizontalPosition: Int = 0

    private var currentDepth: Int = 0

    private var currentAim:Int = 0

    val horizontalPosition
        get() = currentHorizontalPosition

    val depth
        get() = currentDepth

    fun forward(units: Int): Position {
        currentHorizontalPosition += units
        currentDepth += currentAim * units

        return this
    }

    fun down(units: Int): Position {
        currentAim += units

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

    override fun toString(): String {
        return "Position(horizontal position=$currentHorizontalPosition, depth=$currentDepth, aim=$currentAim)"
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
        val units = try {
            actionWithCnt.last().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("unit for action '$text' isn't a number")
        }

        return when (action) {
            "forward" -> {
                ForwardAction(units)
            }
            "down" -> {
                DownAction(units)
            }
            "up" -> {
                UpAction(units)
            }
            else -> {
                throw IllegalArgumentException("unknown action '$action'")
            }
        }
    }
}

class ForwardAction(private val units: Int) : Action {
    override fun doWithPosition(position: Position) {
        position.forward(units)
    }

    override fun toString(): String {
        return "ForwardAction(units=$units)"
    }
}

class DownAction(private val units: Int) : Action {
    override fun doWithPosition(position: Position) {
        position.down(units)
    }

    override fun toString(): String {
        return "DownAction(units=$units)"
    }


}

class UpAction(private val units: Int) : Action {
    override fun doWithPosition(position: Position) {
        position.up(units)
    }

    override fun toString(): String {
        return "UpAction(units=$units)"
    }
}
