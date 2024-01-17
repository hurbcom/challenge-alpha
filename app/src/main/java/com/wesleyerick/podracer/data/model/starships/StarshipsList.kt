package com.wesleyerick.podracer.data.model.starships

data class StarshipsList(
    val count: Int,
    val next: String,
    val previous: Any,
    val starshipsList: List<Starship>
)