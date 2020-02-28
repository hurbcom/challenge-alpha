package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Pagination(

    @SerializedName("count") val count: Int,
    @SerializedName("firstPage") val firstPage: String,
    @SerializedName("nextPage") val nextPage: String,
    @SerializedName("previousPage") val previousPage: String,
    @SerializedName("lastPage") val lastPage: String
)