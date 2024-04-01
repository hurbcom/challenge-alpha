package br.com.gstoduto.starwars.ui.uistates.specie

import br.com.gstoduto.starwars.model.Specie

sealed class SpecieUiState {
    object Loading : SpecieUiState()

    object Empty : SpecieUiState()

    data class Success(
        val species: List<Specie> = emptyList()
    ) : SpecieUiState()

}