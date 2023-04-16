package br.com.hurbandroidchallenge.domain.use_case.planets

import br.com.hurbandroidchallenge.data.repository.PlanetsRepository
import br.com.hurbandroidchallenge.domain.model.Planet
import kotlinx.coroutines.flow.Flow

class SetFavoritePlanetUseCase(
    private val repository: PlanetsRepository
) {
    operator fun invoke(planet: Planet): Flow<Planet> {
        return repository.updateItem(planet.copy(favorite = !planet.favorite))
    }
}