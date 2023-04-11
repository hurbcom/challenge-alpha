package com.example.challengealphaandroid.common

object Utils {
    fun extractNumberFromUrl(url: String): Int {
        val pattern = "/(\\d+)/$".toRegex()
        val matchResult = pattern.find(url)
        val matchGroup = matchResult?.groupValues?.get(1)
        return matchGroup?.toIntOrNull() ?: 0
    }
}