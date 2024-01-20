package br.com.mdr.starwars.domain.repository

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
    fun searchCharacters(query: String): Flow<PagingData<Character>>
    suspend fun getCharacter(name: String): Character
    suspend fun setFavorite(isFavorite: Boolean, name: String)
    suspend fun saveLastSeen(character: Character)
}
