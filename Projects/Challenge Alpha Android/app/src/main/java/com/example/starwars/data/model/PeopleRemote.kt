package com.example.starwars.data.model

import com.google.gson.annotations.SerializedName

data class PeopleRemote(
    @SerializedName("url")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("mass")
    val mass: String,
    @SerializedName("birth_year")
    val birthYear: String,
    @SerializedName("eye_color")
    val eyeColor: String
)