package br.com.hurbandroidchallenge.data.repository

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse
import br.com.hurbandroidchallenge.data.remote.util.apiCall
import br.com.hurbandroidchallenge.domain.model.HomeCategories
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StarWarsBookRepositoryImpl(
    private val remoteDataSource: StarWarsBookRemoteDataSource,
    private val homeCategoriesResponseToEntityMapper: Mapper<HomeCategoriesResponse, HomeCategories>
) : StarWarsBookRepository {
    override suspend fun getHomeCategories(): Flow<HomeCategories> {
        return flow {
            apiCall {
                val response = remoteDataSource.getHomeCategories()
                val mappedResponse = homeCategoriesResponseToEntityMapper.map(response)
                emit(mappedResponse)
            }
        }
    }

}