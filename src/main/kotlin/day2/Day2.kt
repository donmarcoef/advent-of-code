package day2

import readInput

/**
 * @author Team Nexus
 **/
fun main() {
    val position = Position()

    readInput("actions", object {}::class.java).map {
        ActionFactory.forText(it)
    }.forEach {
        position.runActions(it)

        position
    }

    println("${position.horizontalPosition * position.depth} ${position.horizontalPosition} ${position.depth}")
}
