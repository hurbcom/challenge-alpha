package br.com.hurbandroidchallenge.presentation.screens.character.detail.ui

import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.Planet

data class CharacterUI(
    val character: People? = null,
    val films: List<Film> = emptyList(),
    val homeWorld: Planet? = null,
    val favorite: Boolean = false
)