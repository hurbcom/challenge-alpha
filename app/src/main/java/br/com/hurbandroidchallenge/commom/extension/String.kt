package br.com.hurbandroidchallenge.commom.extension

import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import org.apache.commons.lang3.StringUtils

fun CharSequence.containsIgnoringAccent(
    other: CharSequence,
    ignoreCase: Boolean = false
): Boolean {
    val normalizedString = StringUtils.stripAccents(this.toString()).lowercase()
    return normalizedString.contains(other, ignoreCase = ignoreCase)
}

fun String.toDate(): String {
    return DateUtils.getClientPatternDate(this)
}

fun String.idFromUrl(): Int {
    return this.split("/").last { it.isNotBlank() }.toInt()
}