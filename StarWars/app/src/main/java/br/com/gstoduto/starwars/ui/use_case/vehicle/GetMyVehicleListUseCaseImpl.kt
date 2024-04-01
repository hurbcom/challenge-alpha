package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.database.entities.VehicleEntity
import br.com.gstoduto.starwars.repositories.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyVehicleListUseCaseImpl @Inject constructor(
    private val repository: VehicleRepository,
): GetMyVehicleListUseCase {
    override suspend fun getMyListVehicles(): Flow<List<VehicleEntity>> {
        return repository.myList()
    }
}