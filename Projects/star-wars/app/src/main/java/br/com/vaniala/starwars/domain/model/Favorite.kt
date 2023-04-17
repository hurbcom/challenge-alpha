package br.com.vaniala.starwars.domain.model

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
data class Favorite(
    val films: List<Film>,
    val characters: List<People>,
)
