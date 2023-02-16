package com.example.test.domain.usecases.home

import com.example.core.base.BaseResult
import com.example.core.base.UseCaseWithParams
import com.example.test.domain.models.Person
import com.example.test.domain.repositories.home.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) : UseCaseWithParams<ListGetParams, Flow<BaseResult<List<Person>>>>() {
    override fun performAction(requestData: ListGetParams): Flow<BaseResult<List<Person>>> {
        with(requestData) {
            return peopleRepository.getPeople(page, search)
        }
    }
}