package br.com.hurbandroidchallenge.data.repository

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapper
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.model.CategoryResponse
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse
import br.com.hurbandroidchallenge.data.remote.model.PeopleResponse
import br.com.hurbandroidchallenge.data.remote.util.apiCall
import br.com.hurbandroidchallenge.domain.model.Category
import br.com.hurbandroidchallenge.domain.model.HomeCategories
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.flow

class StarWarsBookRepositoryImpl(
    private val remoteDataSource: StarWarsBookRemoteDataSource,
    private val homeCategoriesResponseToEntityMapper: Mapper<HomeCategoriesResponse, HomeCategories>,
    private val peopleResponseToEntityMapper: PagedListMapper<PeopleResponse, People>
) : StarWarsBookRepository {
    override fun getHomeCategories() = flow {
        apiCall {
            val response = remoteDataSource.getHomeCategories()
            emit(homeCategoriesResponseToEntityMapper.map(response))
        }
    }

    override fun getCharacters(url: String) = flow {
        apiCall {
            val response = remoteDataSource.getCharacters(url = url)
            emit(peopleResponseToEntityMapper.map(response))
        }
    }
}