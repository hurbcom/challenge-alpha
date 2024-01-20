package br.com.mdr.starwars.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    val title: String?,
    val episodeId: Int?,
    @SerializedName("opening_crawl") val openingCrawl: String?,
    val director: String?,
    val producer: String?,
    @SerializedName("release_date") val releaseDate: String?,
    val characters: List<String>?,
    val planets: List<String>?,
    val starships: List<String>?,
    val vehicles: List<String>?,
    val species: List<String>?,
    val url: String?
)
