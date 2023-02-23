package com.example.test.data.repositories.home

import com.example.core.base.data.BaseResult
import com.example.test.data.datasources.network.StarWarsApi
import com.example.test.data.mappers.DataMapper.map
import com.example.test.domain.models.Starship
import com.example.test.domain.repositories.home.StarshipsRepository
import com.example.test.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StarshipsRepositoryImpl @Inject constructor(
    private val starWarsApi: StarWarsApi
) : StarshipsRepository {
    override fun getStarships(page: Int, search: String?): Flow<BaseResult<List<Starship>>> = flow {
        starWarsApi.getStarships(page, search.orEmpty()).apply {
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