package com.example.challenge_alpha.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count") val count: Int? = null,
    @SerializedName("firstPage") val firstPage: String? = null,
    @SerializedName("nextPage") val nextPage: String? = null,
    @SerializedName("previousPage") val previousPage: String? = null,
    @SerializedName("lastPage") val lastPage: String? = null
    )