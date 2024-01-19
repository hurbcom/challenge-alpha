package com.br.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class Specie(
    @SerializedName("average_height")
    val averageHeight: String,

    @SerializedName("average_lifespan")
    val averageLifespan: String,
    val classification: String,
    val created: String,
    val designation: String,
    val edited: String,

    @SerializedName("eye_colors")
    val eyeColors: String,
    val films: List<String>,

    @SerializedName("hair_colors")
    val hairColors: String,

    @SerializedName("homeworld")
    val homeWorld: String,
    val language: String,
    val name: String,
    val people: List<String>,

    @SerializedName("skin_colors")
    val skinColors: String,
    val url: String
)