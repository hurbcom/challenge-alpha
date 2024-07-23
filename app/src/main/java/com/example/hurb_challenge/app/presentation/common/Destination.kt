package com.example.hurb_challenge.app.presentation.common

import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.model.Film
import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object Films : Destination()

    @Serializable
    data object Characters : Destination()

    @Serializable
    data class FilmDetails(
        val film: Film
    ) : Destination()

    @Serializable
    data class CharactersDetails(
        val character: Character
    ) : Destination()
}