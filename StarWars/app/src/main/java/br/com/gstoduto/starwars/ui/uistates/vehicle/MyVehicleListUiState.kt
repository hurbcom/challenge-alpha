package br.com.gstoduto.starwars.ui.uistates.vehicle

import br.com.gstoduto.starwars.model.Vehicle

sealed class MyVehicleListUiState {

    object Loading : MyVehicleListUiState()

    data class Empty(
        val vehicles: List<Vehicle> = emptyList()
    ) : MyVehicleListUiState()

    data class Success(
        val vehicles: List<Vehicle> = emptyList()
    ) : MyVehicleListUiState()

}

