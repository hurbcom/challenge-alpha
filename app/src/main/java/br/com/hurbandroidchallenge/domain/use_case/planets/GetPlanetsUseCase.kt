package br.com.hurbandroidchallenge.domain.use_case.planets

import br.com.hurbandroidchallenge.data.repository.PlanetsRepository
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.model.PagedList
import kotlinx.coroutines.flow.Flow

class GetPlanetsUseCase(
    private val repository: PlanetsRepository,
) {
    operator fun invoke(url: String, clearLocalDatasource: Boolean = false) =
        repository.getItemList(url, clearLocalDatasource)
}