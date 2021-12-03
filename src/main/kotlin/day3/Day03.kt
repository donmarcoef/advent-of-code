package day3

import readInput
import kotlin.math.pow

/**
 * @author Team Nexus
 **/
fun binaryDiagnostic(binayNumbers:List<String>):Int {
    val positionNumberInformation = mutableMapOf<Int, NumberInformation>()

    binayNumbers.forEach {
        for (i in it.indices)  {
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

    val length = positionNumberInformation.keys.reduce { acc, i -> if (i > acc) i else acc }

    var gamma = ""
    for (i in IntRange(0, length)) {
        gamma += positionNumberInformation[i]?.commonString()?:throw IllegalStateException("no information for position $i")
    }

    val epsilon = flipBinay(gamma)

    return binaryToNumber(gamma) * binaryToNumber(epsilon)
}

fun flipBinay(binay:String):String {
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

fun binaryToNumber(binary:String): Int {
    var finalNumber = 0
    for (i in binary.indices) {
        val exponent = binary.length-i-1
        val digit = binary[i].toString().toInt()

        finalNumber += digit * 2.0.pow(exponent.toDouble()).toInt()
    }

    return finalNumber
}

data class NumberInformation(var nrOfOne:Int=0, var nrOfZero:Int = 0) {

    fun commonString():String {
        return if (nrOfOne > nrOfZero)
            "1"
        else if (nrOfZero > nrOfOne)
            "0"
        else
            throw IllegalStateException("both equal")
    }

}

fun main() {
    println(binaryDiagnostic(readInput("Day03", object {}::class.java)))
}
