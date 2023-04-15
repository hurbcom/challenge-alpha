package br.com.hurbandroidchallenge.domain.use_case.planets

import br.com.hurbandroidchallenge.data.repository.PlanetsRepository

class GetFavoritesPlanetsUseCase(
    private val repository: PlanetsRepository,
) {
    operator fun invoke() = repository.getFavoriteItems()
}