package br.com.hurbandroidchallenge.commom.util.date

import br.com.hurbandroidchallenge.commom.extension.format
import br.com.hurbandroidchallenge.commom.util.date.SimpleDateFormatter.Companion.CLIENT_PATTERN
import br.com.hurbandroidchallenge.commom.util.date.SimpleDateFormatter.Companion.SERVER_PATTERN
import java.time.LocalDate
import java.util.*
import java.util.concurrent.TimeUnit

class DateUtils {
    companion object {
        private val formatter = SimpleDateFormatter()

        fun getCurrentDate() = LocalDate.now().toString()

        fun daysBetweenDates(second: String?, first: String = getCurrentDate()): Long {
            formatter.setPattern(SERVER_PATTERN)
            val firstDate = formatter.format(first)
            val secondDate = formatter.format(second)
            val difference = secondDate.time - firstDate.time
            return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
        }

        fun getClientPatternDate(date: String): String {
            return try {
                getFormattedDate(currentPattern = SERVER_PATTERN, date)
            } catch (e: Exception) {
                ""
            }
        }

        fun getServerPatternDate(date: String): String {
            return try {
                getFormattedDate(currentPattern = CLIENT_PATTERN, date)
            } catch (e: Exception) {
                ""
            }
        }

        private fun getFormattedDate(currentPattern: String, date: String): String {
            val currentPatterDate = getCurrentPatternDate(currentPattern, date)
            formatter.switchPattern()
            return formatter.format(currentPatterDate)
        }

        private fun getCurrentPatternDate(currentPattern: String, date: String): Date {
            formatter.setPattern(currentPattern)
            return formatter.format(date)
        }

    }
}