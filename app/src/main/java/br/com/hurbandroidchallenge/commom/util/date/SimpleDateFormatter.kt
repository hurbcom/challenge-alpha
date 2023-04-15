package br.com.hurbandroidchallenge.commom.util.date

import okio.IOException
import java.text.SimpleDateFormat
import java.util.*

class SimpleDateFormatter {
    private val formatter: SimpleDateFormat = SimpleDateFormat(SERVER_PATTERN, Locale.getDefault())

    fun format(date: String?): Date {
        if (date.isNullOrBlank()) throw IOException()
        return formatter.parse(date) ?: throw IOException()
    }

    fun format(date: Date?): String {
        if (date == null) throw IOException()
        return formatter.format(date) ?: throw IOException()
    }

    private fun getPattern() = formatter.toPattern()

    fun setPattern(pattern: String) {
        validatePattern(pattern)
        formatter.applyPattern(pattern)
    }

    private fun validatePattern(pattern: String) {
        if ((pattern != SERVER_PATTERN) and (pattern != CLIENT_PATTERN)) {
            throw IOException("Date format can only be $SERVER_PATTERN or $CLIENT_PATTERN")
        }
    }


    fun switchPattern() {
        when (getPattern()) {
            CLIENT_PATTERN -> setPattern(SERVER_PATTERN)
            SERVER_PATTERN -> setPattern(CLIENT_PATTERN)
        }
    }

    companion object {
        const val SERVER_PATTERN = "yyyy-MM-dd"
        const val CLIENT_PATTERN = "dd/MM/yyyy"
    }
}