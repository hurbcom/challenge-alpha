package com.example.test.data.datasources.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StarWarsBaseResponse<T>(
    @Json
    val count: Int?,
    @Json
    val next: String?,
    @Json
    val previous: String?,
    @Json
    val results: List<T>?
)
