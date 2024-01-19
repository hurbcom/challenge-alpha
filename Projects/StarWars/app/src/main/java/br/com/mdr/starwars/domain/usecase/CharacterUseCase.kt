package br.com.mdr.starwars.domain.usecase

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterUseCase(
    private val repository: CharacterRepository
) {

    fun getCharacters(query: String): Flow<PagingData<Character>> =
        if (query.isEmpty()) {
            repository.getCharacters()
        } else {
            repository.searchCharacters(query)
        }
}