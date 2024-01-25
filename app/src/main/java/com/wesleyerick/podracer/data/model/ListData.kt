package com.wesleyerick.podracer.data.model

import com.google.gson.annotations.SerializedName

data class ListData<T>(
    @SerializedName("count")
    val count: Int = 0,

    @SerializedName("next")
    val next: String = String(),

    @SerializedName("previous")
    val previous: Any = String(),

    @SerializedName("results")
    val results: List<T>
)