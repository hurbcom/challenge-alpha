package com.edufelip.challengealpha.data.network.models

import com.google.gson.annotations.SerializedName

class SpecieResponse (
    @SerializedName("average_height")
    val averageHeight: String,
    @SerializedName("average_lifespan")
    val averageLifespan: String,
    @SerializedName("classification")
    val classification: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("eye_colors")
    val eyeColors: String,
    @SerializedName("homeworld")
    val homeworld: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("skin_colors")
    val skinColors: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("people")
    val people: List<String>,
    @SerializedName("films")
    val films: List<String>,
)