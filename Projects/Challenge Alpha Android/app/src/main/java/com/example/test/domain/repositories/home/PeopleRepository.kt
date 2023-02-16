package com.example.test.domain.repositories.home

import com.example.core.base.BaseResult
import com.example.test.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPeople(page: Int, search: String? = null): Flow<BaseResult<List<Person>>>
}