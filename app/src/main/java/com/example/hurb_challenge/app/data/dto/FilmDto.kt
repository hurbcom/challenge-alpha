package com.example.hurb_challenge.app.data.dto

import com.google.gson.annotations.SerializedName

data class FilmDto(
    val title: String,
    @SerializedName("episode_id") val episode: Int,
    @SerializedName("opening_crawl") val openingCrawl: String,
    val director: String,
    val url: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("characters") val charactersUrl: List<String>

)
