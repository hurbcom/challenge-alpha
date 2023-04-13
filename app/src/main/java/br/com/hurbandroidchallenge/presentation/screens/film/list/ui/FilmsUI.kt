package br.com.hurbandroidchallenge.presentation.screens.film.list.ui

import br.com.hurbandroidchallenge.domain.model.Film

data class FilmsUI(
    val films: List<Film> = emptyList(),
    val nextPage: String? = null
)