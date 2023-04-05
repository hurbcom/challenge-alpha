package com.example.starwars.data.repository

import com.example.starwars.data.model.MoviesPage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(page:String): Flow<ApiResult<MoviesPage>>
}