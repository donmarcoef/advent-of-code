package day9

import readInput
import java.util.*

/**
 * @author Team Nexus
 **/
data class Position(
    val x: Int,
    val y: Int,
)

class Cave(val heights: List<List<Int>>) {
    private val maxY = heights.lastIndex
    private val maxX = heights.first().lastIndex
    private val points: List<Position> = heights.indices.flatMap { y ->
        heights[y].indices.map { x -> Position(x, y) }
    }

    fun heightAt(position: Position) =
        if (position.x > maxX || position.x < 0 || position.y < 0 || position.y > maxY) {
            throw IllegalStateException("unknown Position")
        } else {
            heights[position.y][position.x]
        }

    fun getNeighbours(point: Position): List<Position> {
        val (x, y) = point
        val left = if (x == 0) null else Position(x - 1, y)
        val top = if (y == 0) null else Position(x, y - 1)
        val right = if (x == maxX) null else Position(x + 1, y)
        val bottom = if (y == maxY) null else Position(x, y + 1)

        return listOfNotNull(left, top, right, bottom)
    }

    fun isLow(point: Position) = getNeighbours(point).all { neighbour ->
        heightAt(neighbour) > heightAt(point)
    }

    fun getLows() = points.filter { point -> isLow(point) }

    fun getBasinSize(point: Position): Int {
        val accountedFor = mutableSetOf(point)
        val queue = LinkedList<Position>().also { it.add(point) }
        var basinSize = 0
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            if (heightAt(next) != 9) {
                basinSize++
                getNeighbours(next).let { neighbours ->
                    queue.addAll(neighbours.filter { !accountedFor.contains(it) })
                    accountedFor.addAll(neighbours)
                }
            }
        }
        return basinSize
    }
}

fun toHeightMap(values: List<String>) =
    values.map { line ->
        line.map { it.digitToInt() }
    }

private fun part1(heightMap: List<List<Int>>): Int {
    val cave = Cave(heightMap)
    val lows = cave.getLows()
    val riskFactors = lows.map { cave.heightAt(it) + 1 }
    return riskFactors.sum()
}

private fun part2(heightMap: List<List<Int>>): Int {
    val cave = Cave(heightMap)
    val lows = cave.getLows()
    val basinSizes = lows.map { cave.getBasinSize(it) }
    return basinSizes.sortedDescending().take(3).reduce { a, b -> a * b }
}

fun main() {
    val heightMap = toHeightMap(readInput("heights", Position::class.java, true))

    println(part1(heightMap))
    println(part2(heightMap))
}
