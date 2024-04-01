package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface GetVehiclesUseCase {
    suspend fun findVehicles(): Flow<List<Vehicle>>
}