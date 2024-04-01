package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.database.entities.VehicleEntity
import br.com.gstoduto.starwars.repositories.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehicleUseCaseImpl @Inject constructor(
    private val repository: VehicleRepository,
): GetVehicleUseCase {
    override suspend fun findVehicle(id: String): Flow<VehicleEntity?> {
        return repository.findVehicle(id)
    }
}