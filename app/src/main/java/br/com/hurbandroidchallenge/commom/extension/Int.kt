package br.com.hurbandroidchallenge.commom.extension

fun Int.toRoman(): String {
    val decimal = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val roman = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    var output = ""
    var input = this
    decimal.forEachIndexed { index, _ ->
        while (input >= decimal[index]) {
            input -= decimal[index]
            output += roman[index]
        }
    }
    return output
}