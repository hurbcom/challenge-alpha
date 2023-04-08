package br.com.hurbandroidchallenge.commom.extension

import org.apache.commons.lang3.StringUtils

fun CharSequence.containsIgnoringAccent(
    other: CharSequence,
    ignoreCase: Boolean = false
): Boolean {
    val normalizedString = StringUtils.stripAccents(this.toString()).lowercase()
    return normalizedString.contains(other, ignoreCase = ignoreCase)
}