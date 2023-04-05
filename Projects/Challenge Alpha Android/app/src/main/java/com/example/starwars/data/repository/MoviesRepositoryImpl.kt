package com.example.starwars.data.repository

import com.example.starwars.data.model.MoviesPage
import com.example.starwars.data.datasource.MoviesDatasource
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(private val moviesDatasource: MoviesDatasource) : MoviesRepository {
    override suspend fun getMovies(page: String): Flow<ApiResult<MoviesPage>> {
        return moviesDatasource.getMovies(page)
    }
}