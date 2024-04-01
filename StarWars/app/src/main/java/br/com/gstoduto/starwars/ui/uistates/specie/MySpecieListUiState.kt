package br.com.gstoduto.starwars.ui.uistates.specie

import br.com.gstoduto.starwars.model.Specie

sealed class MySpecieListUiState {

    object Loading : MySpecieListUiState()

    data class Empty(
        val species: List<Specie> = emptyList()
    ) : MySpecieListUiState()

    data class Success(
        val species: List<Specie> = emptyList()
    ) : MySpecieListUiState()

}

