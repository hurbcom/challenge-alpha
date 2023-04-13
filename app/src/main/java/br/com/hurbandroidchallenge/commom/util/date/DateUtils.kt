package br.com.hurbandroidchallenge.commom.util.date

import br.com.hurbandroidchallenge.commom.util.date.SimpleDateFormatter.Companion.CLIENT_PATTERN
import br.com.hurbandroidchallenge.commom.util.date.SimpleDateFormatter.Companion.SERVER_PATTERN
import java.time.LocalDate
import java.util.*

class DateUtils {
    companion object {
        private val formatter = SimpleDateFormatter()

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