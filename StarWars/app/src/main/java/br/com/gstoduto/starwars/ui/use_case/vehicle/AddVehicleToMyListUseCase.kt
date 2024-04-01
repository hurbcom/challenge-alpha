package br.com.gstoduto.starwars.ui.use_case.vehicle

interface AddVehicleToMyListUseCase {
    suspend fun addVehicleToMyList(id: String)
}