package com.example.starwars.data.datasource

import com.example.starwars.data.model.MoviesPage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

interface MoviesDatasource {
    fun getMovies(page:String): Flow<ApiResult<MoviesPage>>
}