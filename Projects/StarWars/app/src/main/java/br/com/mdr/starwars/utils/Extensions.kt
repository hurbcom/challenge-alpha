package br.com.mdr.starwars.utils

fun String.upperCaseFirstChar(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun Int.isEven(): Boolean = this % 2 == 0
