package com.example.hurb_challenge.app.data.repository

import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRepository {

    override suspend fun getCharactersByPage(page: Int) = flow { emit(apiService.getCharacters(page)) }
}
