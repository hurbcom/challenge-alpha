package br.com.gstoduto.starwars.ui.uistates.vehicle

import br.com.gstoduto.starwars.model.Vehicle

sealed class VehicleUiState {
    object Loading : VehicleUiState()

    object Empty : VehicleUiState()

    data class Success(
        val vehicles: List<Vehicle> = emptyList()
    ) : VehicleUiState()

}