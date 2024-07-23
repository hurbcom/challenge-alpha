package com.example.hurb_challenge.app.data.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    val name: String,
    val height: String,
    val gender: String,
    val url: String,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("vehicles") val vehiclesUrl: List<String>,
    @SerializedName("starships") val starshipsUrl: List<String>
)
