package com.example.test.data.repositories.home

import com.example.core.base.data.BaseResult
import com.example.test.data.datasources.network.StarWarsApi
import com.example.test.data.mappers.DataMapper.map
import com.example.test.domain.models.Person
import com.example.test.domain.repositories.home.PeopleRepository
import com.example.test.utils.Constants.NEXT_PAGE_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val starWarsApi: StarWarsApi
) : PeopleRepository {
    override fun getPeople(page: Int, search: String?): Flow<BaseResult<List<Person>>> = flow {
        starWarsApi.getPeople(page, search.orEmpty()).apply {
            results?.run {
                emit(BaseResult.Success(results.map { it.map() }, hashMapOf(NEXT_PAGE_KEY to next)))
            } ?: emit(BaseResult.Error(IllegalArgumentException()))
        }
    }.catch { emit(BaseResult.Error(it)) }
        .flowOn(Dispatchers.IO)
}