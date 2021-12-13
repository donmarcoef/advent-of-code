import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, clazz: Class<*>? = null, removeBlankLines:Boolean=false) =
    (clazz ?: object {}.javaClass)
        .getResource("$name.txt").readText().lines().filter { if (removeBlankLines) it.isNotBlank() else true }.toList()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
