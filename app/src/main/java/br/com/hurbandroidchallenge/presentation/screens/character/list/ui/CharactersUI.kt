package br.com.hurbandroidchallenge.presentation.screens.character.list.ui

import br.com.hurbandroidchallenge.domain.model.People

data class CharactersUI(
    val characters: List<People> = emptyList(),
    val nextPage: String? = null
)