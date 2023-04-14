package br.com.hurbandroidchallenge.domain.use_case.planets

import br.com.hurbandroidchallenge.data.repository.PlanetsRepository
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow

class GetPlanetByUrlUseCase(
    private val repository: PlanetsRepository,
) {
    operator fun invoke(url: String): Flow<Planet> {
        return repository.getItemByUrl(url)
    }
}