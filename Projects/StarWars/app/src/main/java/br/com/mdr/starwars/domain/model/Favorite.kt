package br.com.mdr.starwars.domain.model

data class Favorite(
    val films: List<Film>,
    val characters: List<Character>
)
