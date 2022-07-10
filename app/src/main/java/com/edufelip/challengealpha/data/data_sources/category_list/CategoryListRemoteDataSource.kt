package com.edufelip.challengealpha.data.data_sources.category_list

import com.edufelip.challengealpha.data.network.base.PagedListResponse
import com.edufelip.challengealpha.data.network.models.*

interface CategoryListRemoteDataSource {
    suspend fun getPeopleList(
        search: String?,
        page: Int,
    ): PagedListResponse<PeopleResponse>

    suspend fun getFilmList(
        search: String?,
        page: Int,
    ): PagedListResponse<FilmResponse>

    suspend fun getStarshipList(
        search: String?,
        page: Int,
    ): PagedListResponse<StarshipResponse>

    suspend fun getVehicleList(
        search: String?,
        page: Int,
    ): PagedListResponse<VehicleResponse>

    suspend fun getPlanetList(
        search: String?,
        page: Int,
    ): PagedListResponse<PlanetResponse>

    suspend fun getSpecieList(
        search: String?,
        page: Int,
    ): PagedListResponse<SpecieResponse>
}