package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.data.repository.PlanetsRepository
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import kotlinx.coroutines.flow.Flow

class GetPlanetsUseCase(
    private val repository: PlanetsRepository,
) {
    operator fun invoke(url: String): Flow<PagedList<Planet>> {
        return repository.getItemList(url)
    }
}