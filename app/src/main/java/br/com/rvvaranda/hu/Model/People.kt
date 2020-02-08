package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("count")
    val count: Int,
    @SerializedName("filter")
    val filter: String,
    @SerializedName("term")
    val term: String
)