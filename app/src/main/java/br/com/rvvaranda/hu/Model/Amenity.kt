package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Amenity(
    @SerializedName("category")
    val category: String,
    @SerializedName("name")
    val name: String
)