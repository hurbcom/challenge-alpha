package com.example.starwars.data

import com.example.starwars.data.model.MoviesPage
import com.example.starwars.data.model.PeoplePage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {
    @GET("films")
    fun getMovies(@Query("page") page: String): Flow<ApiResult<MoviesPage>>

    @GET("people")
    fun getPeoples(@Query("page") page: String): Flow<ApiResult<PeoplePage>>
}