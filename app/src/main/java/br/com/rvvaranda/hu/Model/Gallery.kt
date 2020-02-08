package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Gallery(
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String
)