package br.com.mdr.starwars.data.remote.model

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    val name: String?,
    val height: String?,
    val mass: String?,
    @SerializedName("hair_color") val hairColor: String?,
    @SerializedName("skin_color") val skinColor: String?,
    @SerializedName("birth_year") val birthYear: String?,
    val gender: String?,
    @SerializedName("homeworld") val homeWorld: String?,
    val url: String?,
    val films: List<String>?,
    val vehicles: List<String>?,
    val starships: List<String>?
)
