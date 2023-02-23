package com.example.test.domain.usecases.home

import com.example.core.base.data.BaseResult
import com.example.core.base.domain.UseCaseWithParams
import com.example.test.domain.mappers.DomainMappers.mapPlanets
import com.example.test.domain.repositories.home.PlanetsRepository
import com.example.test.domain.usecases.ListGetParams
import com.example.test.presentation.models.CategoryItemDetailsViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val planetsRepository: PlanetsRepository
) : UseCaseWithParams<ListGetParams, Flow<BaseResult<List<CategoryItemDetailsViewData>>>>() {
    override fun performAction(requestData: ListGetParams): Flow<BaseResult<List<CategoryItemDetailsViewData>>> {
        with(requestData) {
            return planetsRepository.getPlanets(page, search).map { it.mapPlanets() }
        }
    }
}