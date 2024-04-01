package br.com.gstoduto.starwars.ui.use_case.vehicle

import br.com.gstoduto.starwars.repositories.VehicleRepository
import javax.inject.Inject

class RemoveVehicleFromMyListUseCaseImpl @Inject constructor(
    private val repository: VehicleRepository,
): RemoveVehicleFromMyListUseCase {
    override suspend fun removeVehicleFromMyList(id: String) {
        return repository.removeFromMyList(id)
    }
}