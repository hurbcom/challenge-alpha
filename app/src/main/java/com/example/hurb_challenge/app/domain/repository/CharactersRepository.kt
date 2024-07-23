package com.example.hurb_challenge.app.domain.repository

import com.example.hurb_challenge.app.data.dto.ApiResponse
import com.example.hurb_challenge.app.data.dto.CharacterDto
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharactersByPage(page: Int): Flow<ApiResponse<CharacterDto>>
}
