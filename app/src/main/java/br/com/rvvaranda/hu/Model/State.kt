package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("count")
    val count: Int,
    @SerializedName("filter")
    val filter: String,
    @SerializedName("term")
    val term: String
)