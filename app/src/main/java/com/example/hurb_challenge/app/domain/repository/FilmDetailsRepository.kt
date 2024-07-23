package com.example.hurb_challenge.app.domain.repository

import com.example.hurb_challenge.app.data.Result
import com.example.hurb_challenge.app.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FilmDetailsRepository {

    suspend fun getCharactersByUrls(urls: List<String>): Flow<Result<List<Character>>>
}
