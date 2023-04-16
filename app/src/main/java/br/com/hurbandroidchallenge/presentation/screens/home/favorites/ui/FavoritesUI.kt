package br.com.hurbandroidchallenge.presentation.screens.home.favorites.ui

import br.com.hurbandroidchallenge.presentation.model.SmallItemModel

data class FavoritesUI(
    val characters: List<SmallItemModel> = emptyList(),
    val films: List<SmallItemModel> = emptyList(),
    val planets: List<SmallItemModel> = emptyList(),
)