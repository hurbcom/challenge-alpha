package com.isranascimento.network.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

const val TIMEOUT_SECS = 5L

inline fun <reified T> createRestClient(server: MockWebServer, url: String = "/"): T {
    val client = OkHttpClient
        .Builder()
        .connectTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
        .build()
    return Retrofit.Builder()
        .baseUrl(server.url(url))
        .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
        .client(client)
        .build()
        .create(T::class.java)
}

fun Any.mockResponse(jsonFilePath: String): MockResponse {
    return MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(readJsonFromFile(jsonFilePath))
}

private fun Any.readJsonFromFile(filePath: String): String {
    val resource = this::class.java.classLoader!!.getResource(filePath)
    if (resource != null) {
        val inputStream = resource.openStream()
        val stringBuilder = StringBuilder()
        val bufferedReader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
        bufferedReader.use { stringBuilder.append(it.readText()) }
        return stringBuilder.toString()
    } else {
        throw IllegalArgumentException("File not found. [File Path: $filePath]")
    }
}