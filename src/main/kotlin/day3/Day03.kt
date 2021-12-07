package day3

import readInput
import kotlin.math.pow

/**
 * @author Team Nexus
 **/
fun binaryDiagnostic(binayNumbers: List<String>): Int {
    val positionNumberInformation = analyseNumbers(binayNumbers)

    val length = positionNumberInformation.keys.reduce { acc, i -> if (i > acc) i else acc }

    var gamma = ""
    for (i in IntRange(0, length)) {
        gamma += positionNumberInformation[i]?.mostCommonString()
            ?: throw IllegalStateException("no information for position $i")
    }

    val epsilon = flipBinay(gamma)

    return binaryToNumber(gamma) * binaryToNumber(epsilon)
}

private fun analyseNumbers(binayNumbers: List<String>): MutableMap<Int, NumberInformation> {
    val positionNumberInformation = mutableMapOf<Int, NumberInformation>()

    binayNumbers.forEach {
        for (i in it.indices) {
            val positionInfo = positionNumberInformation[i] ?: NumberInformation()

            val char = it[i]

            if (char == '1') {
                positionInfo.nrOfOne++
            } else {
                positionInfo.nrOfZero++
            }

            positionNumberInformation[i] = positionInfo
        }
    }
    return positionNumberInformation
}

fun lifeSupportingRate(binaryNumbers: List<String>, keep:(numberInformation:MutableMap<Int, NumberInformation>, number:String, pos:Int)->Boolean): Int {
    val length = binaryNumbers.first().length
    var pos = 0
    var toKeep = binaryNumbers.toMutableList()

    while(toKeep.size != 1 && pos < length) {
        val numberAnalyse = analyseNumbers(toKeep)
        val newToKeep = mutableListOf<String>()

        toKeep.forEach {
            if (keep(numberAnalyse, it, pos)) {
                newToKeep.add(it)
            }
        }
        toKeep = newToKeep

        pos++
    }

    if (toKeep.size != 1) {
        throw IllegalStateException("not exactly one number left (${toKeep.size})")
    }

    return binaryToNumber(toKeep.first())
}

fun oxygenGeneratorRating() = { numberInformation:MutableMap<Int, NumberInformation>, binaryNumber:String, pos:Int ->
    val nrOfOne = numberInformation[pos]!!.nrOfOne
    val nrOfZero = numberInformation[pos]!!.nrOfZero
    (binaryNumber[pos] == '1' && nrOfOne >= nrOfZero)
            ||
            (binaryNumber[pos] == '0' && nrOfZero > nrOfOne)
}

fun co2ScrubberRating() = { numberInformation:MutableMap<Int, NumberInformation>, binaryNumber:String, pos:Int ->
    val nrOfOne = numberInformation[pos]!!.nrOfOne
    val nrOfZero = numberInformation[pos]!!.nrOfZero
    (binaryNumber[pos] == '1' && nrOfOne < nrOfZero)
            ||
            (binaryNumber[pos] == '0' && nrOfZero <= nrOfOne)
}

fun flipBinay(binay: String): String {
    var finalString = ""
    binay.forEach {
        if (it == '1') {
            finalString += "0"
        } else {
            finalString += "1"
        }
    }

    return finalString
}

fun binaryToNumber(binary: String): Int {
    var finalNumber = 0
    for (i in binary.indices) {
        val exponent = binary.length - i - 1
        val digit = binary[i].toString().toInt()

        finalNumber += digit * 2.0.pow(exponent.toDouble()).toInt()
    }

    return finalNumber
}

data class NumberInformation(var nrOfOne: Int = 0, var nrOfZero: Int = 0) {

    fun mostCommonString(): String {
        return if (nrOfOne >= nrOfZero)
            "1"
        else
            "0"
    }

    fun leastCommonString(): String {
        return if (nrOfOne <= nrOfZero)
            "0"
        else
            "1"
    }

}

fun main() {
    val binaryNumbers = readInput("Day03", object {}::class.java)
    println("${binaryDiagnostic(binaryNumbers)}")

    val oxygenGeneratorRating = lifeSupportingRate(binaryNumbers, oxygenGeneratorRating())
    val co2ScrubberRating = lifeSupportingRate(binaryNumbers, co2ScrubberRating())

    println(oxygenGeneratorRating * co2ScrubberRating)
}
