package com.example.starwars.presentation.ext

import java.text.SimpleDateFormat
import java.util.*

const val BEAUTIFUL_DATE = "dd-MM-yyyy"
const val RAW_DATE = "yyyy-MM-dd"

fun Date.format(format: String): String {
    return SimpleDateFormat(format, Locale.US).format(this)
}

fun String.toDate(format: String): Date {
    return try {
        SimpleDateFormat(format, Locale.US).parse(this)!!
    } catch (e: Exception) {
        throw IllegalArgumentException("$this is an invalid date format")
    }
}


fun String.beautifyDate(): String {
    return this.toDate(RAW_DATE).format(BEAUTIFUL_DATE).replace("-", "/")
}

