package com.example.hurb_challenge.app.data.repository

import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FilmsRepository {

    override suspend fun getFilms(page: Int) = flow { emit(apiService.getFilms(page)) }
}