package com.edufelip.challengealpha.data.network.service

import com.edufelip.challengealpha.data.network.base.PagedListResponse
import com.edufelip.challengealpha.data.network.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/people/")
    suspend fun getPeopleList(
        @Query("search") search: String?,
        @Query("page") page: Int,
    ): Response<PagedListResponse<PeopleResponse>>

    @GET("/api/films/")
    suspend fun getFilmList(
        @Query("search") search: String?,
        @Query("page") page: Int,
    ): Response<PagedListResponse<FilmResponse>>

    @GET("/api/starships/")
    suspend fun getStarshipList(
        @Query("search") search: String?,
        @Query("page") page: Int,
    ): Response<PagedListResponse<StarshipResponse>>

    @GET("/api/vehicles/")
    suspend fun getVehiclesList(
        @Query("search") search: String?,
        @Query("page") page: Int,
    ): Response<PagedListResponse<VehicleResponse>>

    @GET("/api/planets/")
    suspend fun getPlanetsList(
        @Query("search") search: String?,
        @Query("page") page: Int,
    ): Response<PagedListResponse<PlanetResponse>>

    @GET("/api/species/")
    suspend fun getSpeciesList(
        @Query("search") search: String?,
        @Query("page") page: Int,
    ): Response<PagedListResponse<SpecieResponse>>
}