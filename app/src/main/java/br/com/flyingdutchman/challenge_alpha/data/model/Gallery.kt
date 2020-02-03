package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class Gallery(
    @SerializedName("url")
    val url: String
)