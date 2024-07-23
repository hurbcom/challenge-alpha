package com.example.hurb_challenge.app.domain.usecase

import com.example.hurb_challenge.app.domain.repository.CharacterDetailsRepository
import javax.inject.Inject

class GetCharacterStarshipsUseCase @Inject constructor(
    private val repository: CharacterDetailsRepository
) {
    suspend fun call(urls: List<String>) = repository.getStarships(urls)
}
