package br.com.hurbandroidchallenge.domain.use_case.planets

import br.com.hurbandroidchallenge.data.repository.PlanetsRepository

class GetLastSeenPlanetsUseCase(
    private val repository: PlanetsRepository,
) {
    operator fun invoke() = repository.getLastSeenItems()
}