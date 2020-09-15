package art.manguste.android.ArasakaWeapons.Util

import java.util.*

fun getOrderNum(): String{
    return getRandomStr(2, true) + getRandomNumber(6)
}

fun getAssignedDroneId(): String{
    return getRandomNumber(2) + "_x"+ getRandomStr(1, false) + getRandomNumber(2)
}

fun getRandomNumber(length: Int = 1): String {
    var res = ""
    if (length > 0) {
        for (i in 0 until length){
            res += (0..9).shuffled().last()
        }
    }
    return res
}

fun getRandomStr(length: Int = 1, upperCase: Boolean = false): String {
    val chars = ('a'..'z')
    var res = List(length) { chars.random() }.joinToString("")
    res = if (!upperCase) res.toLowerCase(Locale.getDefault()) else res.toUpperCase(Locale.getDefault())
    return res
}