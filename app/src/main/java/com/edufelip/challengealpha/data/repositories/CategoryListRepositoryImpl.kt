package com.edufelip.challengealpha.data.repositories

import com.edufelip.challengealpha.common.mapper.PagedListMapper
import com.edufelip.challengealpha.data.data_sources.category_list.CategoryListRemoteDataSource
import com.edufelip.challengealpha.data.network.models.*
import com.edufelip.challengealpha.domain.models.*
import com.edufelip.challengealpha.domain.models.base.PagedList
import com.edufelip.challengealpha.domain.repositories.CategoryListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryListRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoryListRemoteDataSource,
    private val filmListResponseToEntityMapper: PagedListMapper<FilmResponse, Film>,
    private val peopleListResponseToEntityMapper: PagedListMapper<PeopleResponse, People>,
    private val planetListResponseToEntityMapper: PagedListMapper<PlanetResponse, Planet>,
    private val specieListResponseToEntityMapper: PagedListMapper<SpecieResponse, Specie>,
    private val starshipListResponseToEntityMapper: PagedListMapper<StarshipResponse, Starship>,
    private val vehicleListResponseToEntityMapper: PagedListMapper<VehicleResponse, Vehicle>,
) : CategoryListRepository {

    override fun getFilmList(search: String?, page: Int): Flow<PagedList<Film>> {
        return flow {
            val response = remoteDataSource.getFilmList(search, page)
            emit(filmListResponseToEntityMapper.map(response))
        }.flowOn(Dispatchers.IO)
    }

    override fun getPeopleList(search: String?, page: Int): Flow<PagedList<People>> {
        return flow {
            val response = remoteDataSource.getPeopleList(search, page)
            emit(peopleListResponseToEntityMapper.map(response))
        }.flowOn(Dispatchers.IO)
    }

    override fun getPlanetList(search: String?, page: Int): Flow<PagedList<Planet>> {
        return flow {
            val response = remoteDataSource.getPlanetList(search, page)
            emit(planetListResponseToEntityMapper.map(response))
        }.flowOn(Dispatchers.IO)
    }

    override fun getSpecieList(search: String?, page: Int): Flow<PagedList<Specie>> {
        return flow {
            val response = remoteDataSource.getSpecieList(search, page)
            emit(specieListResponseToEntityMapper.map(response))
        }.flowOn(Dispatchers.IO)
    }

    override fun getStarshipList(search: String?, page: Int): Flow<PagedList<Starship>> {
        return flow {
            val response = remoteDataSource.getStarshipList(search, page)
            emit(starshipListResponseToEntityMapper.map(response))
        }.flowOn(Dispatchers.IO)
    }

    override fun getVehicleList(search: String?, page: Int): Flow<PagedList<Vehicle>> {
        return flow {
            val response = remoteDataSource.getVehicleList(search, page)
            emit(vehicleListResponseToEntityMapper.map(response))
        }.flowOn(Dispatchers.IO)
    }
}