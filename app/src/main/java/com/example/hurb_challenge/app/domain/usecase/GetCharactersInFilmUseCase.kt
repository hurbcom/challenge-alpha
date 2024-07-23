package com.example.hurb_challenge.app.domain.usecase

import com.example.hurb_challenge.app.data.Result
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.repository.FilmDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersInFilmUseCase @Inject constructor(
    private val repository: FilmDetailsRepository
) {
    suspend fun call(urls: List<String>): Flow<Result<List<Character>>> =
        repository.getCharactersByUrls(urls)
}
