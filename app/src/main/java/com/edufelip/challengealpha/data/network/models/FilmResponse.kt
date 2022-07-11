package com.edufelip.challengealpha.data.network.models

import com.google.gson.annotations.SerializedName

class FilmResponse(
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("director")
    val director: String,
    @SerializedName("episode_id")
    val episodeId: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    @SerializedName("planets")
    val planets: List<String>,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("species")
    val species: List<String>,
    @SerializedName("starships")
    val starships: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vehicles")
    val vehicles: List<String>
)