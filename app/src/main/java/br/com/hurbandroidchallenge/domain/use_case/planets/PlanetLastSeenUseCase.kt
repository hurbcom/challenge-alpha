package br.com.hurbandroidchallenge.domain.use_case.planets

import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.repository.PlanetsRepository
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.Planet
import kotlinx.coroutines.flow.Flow

class PlanetLastSeenUseCase(
    private val repository: PlanetsRepository
) {
    operator fun invoke(planet: Planet): Flow<Unit> {
        val now = DateUtils.getCurrentDate()
        return repository.updateItem(planet.copy(lastSeen = now))
    }
}