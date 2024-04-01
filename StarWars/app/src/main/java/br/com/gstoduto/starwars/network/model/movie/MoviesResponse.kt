package br.com.gstoduto.starwars.network.model.movie

data class MoviesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<MovieResponse>
)