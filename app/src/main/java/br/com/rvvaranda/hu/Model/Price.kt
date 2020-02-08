package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("count")
    val count: Int,
    @SerializedName("filter")
    val filter: String,
    @SerializedName("maxExclusive")
    val maxExclusive: Int,
    @SerializedName("min")
    val min: Int
)