package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count")
    val count: Int,
    @SerializedName("firstPage")
    val firstPage: String,
    @SerializedName("lastPage")
    val lastPage: String,
    @SerializedName("nextPage")
    val nextPage: Any,
    @SerializedName("previousPage")
    val previousPage: Any
)