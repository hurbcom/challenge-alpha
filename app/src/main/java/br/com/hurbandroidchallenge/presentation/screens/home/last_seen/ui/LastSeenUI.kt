package br.com.hurbandroidchallenge.presentation.screens.home.last_seen.ui

import br.com.hurbandroidchallenge.presentation.model.SmallItemModel

data class LastSeenUI(
    val characters: List<SmallItemModel> = emptyList(),
    val films: List<SmallItemModel> = emptyList(),
    val planets: List<SmallItemModel> = emptyList(),
)