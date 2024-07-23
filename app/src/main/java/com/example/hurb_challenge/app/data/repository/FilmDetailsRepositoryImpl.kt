package com.example.hurb_challenge.app.data.repository

import com.example.hurb_challenge.app.core.extractNumbers
import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.data.Result
import com.example.hurb_challenge.app.data.asResult
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.repository.FilmDetailsRepository
import com.example.hurb_challenge.app.domain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FilmDetailsRepository {

    override suspend fun getCharactersByUrls(urls: List<String>): Flow<Result<List<Character>>> {
        return flow<List<Character>> {
            val charactersDto = mutableListOf<Character>()
            urls.forEach { url ->
                val dto = apiService.getCharacter(url)
                val id = dto.url.extractNumbers()
                charactersDto.add(dto.toDomain(id))
            }
            emit(charactersDto)
        }.asResult()
    }
}