package com.example.hurb_challenge.app.core

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.extractNumbers() = filter { it.isDigit() }

fun String.encodeUrl(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

fun String.decodeUrl(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())

fun List<String>.encodeUrl() = map { url -> url.encodeUrl() }

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let<String, T>(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }
}