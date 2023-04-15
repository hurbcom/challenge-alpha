package br.com.hurbandroidchallenge.presentation.screens.home.favorites.ui

import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.Planet

data class FavoritesUI(
    val characters: List<People> = emptyList(),
    val films: List<Film> = emptyList(),
    val planets: List<Planet> = emptyList()
)