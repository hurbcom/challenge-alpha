package br.com.hurbandroidchallenge.presentation.screens.planet.list.ui

import br.com.hurbandroidchallenge.domain.model.Planet

data class PlanetsUI(
    val planets: List<Planet> = emptyList(),
    val nextPage: String? = null
)