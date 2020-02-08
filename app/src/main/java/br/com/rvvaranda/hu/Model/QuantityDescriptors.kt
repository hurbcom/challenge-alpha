package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class QuantityDescriptors(
    @SerializedName("maxAdults")
    val maxAdults: Int,
    @SerializedName("maxChildren")
    val maxChildren: Int,
    @SerializedName("maxFreeChildrenAge")
    val maxFreeChildrenAge: Int
)