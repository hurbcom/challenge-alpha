package br.com.hurbandroidchallenge.presentation.screens.categoryDetail.ui

import br.com.hurbandroidchallenge.domain.model.People

data class CharactersUI(
    val categoryItems: List<People> = emptyList(),
    val nextPage: String? = null
)