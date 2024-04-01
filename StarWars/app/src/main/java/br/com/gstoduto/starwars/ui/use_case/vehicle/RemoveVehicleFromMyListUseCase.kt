package br.com.gstoduto.starwars.ui.use_case.vehicle

interface RemoveVehicleFromMyListUseCase {
    suspend fun removeVehicleFromMyList(id: String)
}