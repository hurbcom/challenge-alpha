package br.com.hurbandroidchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

class HomeCategoriesDto(
    @SerializedName("people") val people: String?,
    @SerializedName("planets") val planets: String?,
    @SerializedName("films") val films: String?,
    @SerializedName("species") val species: String?,
    @SerializedName("vehicles") val vehicles: String?,
    @SerializedName("starships") val starships: String?
)