package com.edufelip.challengealpha.data.network.base

fun extractIdFromUrl(url: String): String {
    return url.split("api/")[1].split("/")[1]
}