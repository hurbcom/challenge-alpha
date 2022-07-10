package com.edufelip.challengealpha.data.data_sources.category_list

import com.edufelip.challengealpha.data.network.base.PagedListResponse
import com.edufelip.challengealpha.data.network.models.*
import com.edufelip.challengealpha.data.network.service.ApiService
import retrofit2.HttpException
import javax.inject.Inject

class CategoryListRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
) : CategoryListRemoteDataSource {
    override suspend fun getPeopleList(
        search: String?,
        page: Int,
    ): PagedListResponse<PeopleResponse> {
        val response = service.getPeopleList(search = search, page = page)

        if (response.isSuccessful)
            return response.body()!!
        else
            throw HttpException(response)
    }

    override suspend fun getFilmList(search: String?, page: Int): PagedListResponse<FilmResponse> {
        val response = service.getFilmList(search = search, page = page)

        if (response.isSuccessful)
            return response.body()!!
        else
            throw HttpException(response)
    }

    override suspend fun getStarshipList(
        search: String?,
        page: Int
    ): PagedListResponse<StarshipResponse> {
        val response = service.getStarshipList(search = search, page = page)

        if (response.isSuccessful)
            return response.body()!!
        else
            throw HttpException(response)
    }

    override suspend fun getVehicleList(
        search: String?,
        page: Int
    ): PagedListResponse<VehicleResponse> {
        val response = service.getVehiclesList(search = search, page = page)

        if (response.isSuccessful)
            return response.body()!!
        else
            throw HttpException(response)
    }

    override suspend fun getPlanetList(
        search: String?,
        page: Int
    ): PagedListResponse<PlanetResponse> {
        val response = service.getPlanetsList(search = search, page = page)

        if (response.isSuccessful)
            return response.body()!!
        else
            throw HttpException(response)
    }

    override suspend fun getSpecieList(
        search: String?,
        page: Int
    ): PagedListResponse<SpecieResponse> {
        val response = service.getSpeciesList(search = search, page = page)

        if (response.isSuccessful)
            return response.body()!!
        else
            throw HttpException(response)
    }
}