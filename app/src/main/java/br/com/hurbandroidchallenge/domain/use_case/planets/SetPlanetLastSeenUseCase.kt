package br.com.hurbandroidchallenge.domain.use_case.planets

import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.repository.PlanetsRepository
import br.com.hurbandroidchallenge.domain.model.Planet
import kotlinx.coroutines.flow.Flow

class SetPlanetLastSeenUseCase(
    private val repository: PlanetsRepository
) {
    operator fun invoke(planet: Planet): Flow<Planet> {
        val now = DateUtils.getCurrentDate()
        return repository.updateItem(planet.copy(lastSeen = now))
    }
}