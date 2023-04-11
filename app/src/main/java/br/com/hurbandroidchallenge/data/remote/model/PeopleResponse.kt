package br.com.hurbandroidchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

class PeopleResponse (
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("mass") val mass: String?,
    @SerializedName("hair_color") val hairColor: String?,
    @SerializedName("skin_color") val skinColor: String?,
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("homeworld") val homeWorld: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("vehicles") val vehicles: List<String>?,
    @SerializedName("starships") val starships: List<String>?
)