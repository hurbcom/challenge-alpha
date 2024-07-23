package com.example.hurb_challenge.app.domain.repository

import com.example.hurb_challenge.app.data.dto.ApiResponse
import com.example.hurb_challenge.app.data.dto.FilmDto
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {
    suspend fun getFilms(page: Int): Flow<ApiResponse<FilmDto>>
}
