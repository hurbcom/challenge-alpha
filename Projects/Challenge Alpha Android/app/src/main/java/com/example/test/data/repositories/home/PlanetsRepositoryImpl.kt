package com.example.test.data.repositories.home

import com.example.core.base.data.BaseResult
import com.example.test.data.datasources.network.StarWarsApi
import com.example.test.data.mappers.DataMapper.map
import com.example.test.domain.models.Planet
import com.example.test.domain.repositories.home.PlanetsRepository
import com.example.test.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlanetsRepositoryImpl @Inject constructor(
    private val starWarsApi: StarWarsApi
) : PlanetsRepository {

    override fun getPlanets(page: Int, search: String?): Flow<BaseResult<List<Planet>>> = flow {
        starWarsApi.getPlanets(page, search.orEmpty()).apply {
            results?.run {
                emit(
                    BaseResult.Success(
                        results.map { it.map() },
                        hashMapOf(Constants.NEXT_PAGE_KEY to next)
                    )
                )
            } ?: emit(BaseResult.Error(IllegalArgumentException()))
        }
    }.catch { emit(BaseResult.Error(it)) }
        .flowOn(Dispatchers.IO)
}