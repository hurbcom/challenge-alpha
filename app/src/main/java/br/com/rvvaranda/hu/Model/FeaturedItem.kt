package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class FeaturedItem(
    @SerializedName("amenities")
    val amenities: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)