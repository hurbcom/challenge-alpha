package br.com.hurbandroidchallenge.presentation.screens.planet.detail.ui

import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.Planet

data class PlanetUI(
    val planet: Planet? = null,
    val characters: List<People> = emptyList(),
    val films: List<Film> = emptyList()
)