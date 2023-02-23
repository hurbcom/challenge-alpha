package com.example.test.domain.usecases.home

import com.example.core.base.data.BaseResult
import com.example.core.base.domain.UseCaseWithParams
import com.example.test.domain.mappers.DomainMappers.mapPeople
import com.example.test.domain.repositories.home.PeopleRepository
import com.example.test.domain.usecases.ListGetParams
import com.example.test.presentation.models.CategoryItemDetailsViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) : UseCaseWithParams<ListGetParams, Flow<BaseResult<List<CategoryItemDetailsViewData>>>>() {
    override fun performAction(requestData: ListGetParams): Flow<BaseResult<List<CategoryItemDetailsViewData>>> {
        with(requestData) {
            return peopleRepository.getPeople(page, search).map { it.mapPeople() }
        }
    }
}