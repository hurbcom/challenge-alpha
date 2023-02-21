package com.example.test.domain.usecases.home

import com.example.core.base.BaseResult
import com.example.core.base.UseCaseWithParams
import com.example.test.domain.models.Planet
import com.example.test.domain.repositories.home.PlanetsRepository
import com.example.test.domain.usecases.ListGetParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val planetsRepository: PlanetsRepository
) : UseCaseWithParams<ListGetParams, Flow<BaseResult<List<Planet>>>>() {
    override fun performAction(requestData: ListGetParams): Flow<BaseResult<List<Planet>>> {
        with(requestData) {
            return planetsRepository.getPlanets(page, search)
        }
    }
}