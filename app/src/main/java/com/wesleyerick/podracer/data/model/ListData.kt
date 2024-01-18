package com.wesleyerick.podracer.data.model

import com.google.gson.annotations.SerializedName

data class ListData<T>(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results")
    val results: List<T>
)