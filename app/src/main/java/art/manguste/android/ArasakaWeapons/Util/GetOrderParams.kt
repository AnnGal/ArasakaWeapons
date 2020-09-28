package art.manguste.android.ArasakaWeapons.Util

import java.util.*

// check how Kotlin and Java acts together

/**
 * Random Order number "XX000000"
 * */
fun getOrderNum(): String{
    return getRandomStr(2, true) + getRandomNumber(6)
}

/**
 * Random Drone ID "00_X00"
 * */
fun getAssignedDroneId(): String{
    return getRandomNumber(2) + "_"+ getRandomStr(1, true) + getRandomNumber(2)
}

/**
 * String of random numbers
 * @param length - how many numbers in string
 * */
fun getRandomNumber(length: Int = 1): String {
    var res = ""
    if (length > 0) {
        for (i in 0 until length){
            res += (0..9).shuffled().last()
        }
    }
    return res
}

/**
 * String of random letters
 * @param length - how many letters in string
 * @param upperCase - use upper case letters
 * */
fun getRandomStr(length: Int = 1, upperCase: Boolean = false): String {
    val chars = ('a'..'z')
    var res = List(length) { chars.random() }.joinToString("")
    res = if (!upperCase) res.toLowerCase(Locale.getDefault()) else res.toUpperCase(Locale.getDefault())
    return res
}