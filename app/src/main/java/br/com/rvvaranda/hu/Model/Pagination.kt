package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count")
    val count: Int,
    @SerializedName("firstPage")
    val firstPage: String,
    @SerializedName("lastPage")
    val lastPage: String,
    @SerializedName("nextPage")
    val nextPage: String,
    @SerializedName("previousPage")
    val previousPage: Any
)