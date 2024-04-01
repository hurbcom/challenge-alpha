package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.repositories.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehiclesUseCaseImpl @Inject constructor(
    private val repository: VehicleRepository,
): GetVehiclesUseCase {
    override suspend fun findVehicles(): Flow<List<Vehicle>> {
        return repository.findVehicles()
    }
}