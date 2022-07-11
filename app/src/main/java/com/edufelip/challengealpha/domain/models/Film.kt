package com.edufelip.challengealpha.domain.models

data class Film (
    val id: String,
    val characters: List<String>,
    val director: String,
    val episodeId: String,
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val title: String,
    val vehicles: List<String>,
    val imageUrl: String,
)