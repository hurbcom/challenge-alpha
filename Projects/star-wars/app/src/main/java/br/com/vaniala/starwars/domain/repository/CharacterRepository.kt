package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.People

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
interface CharacterRepository {
    suspend fun insertAll(characters: List<People>)

    suspend fun getCharacters(page: Int): ApiResponse<People>

    suspend fun charactersByName(query: String): List<People>

    suspend fun getCharacterByName(query: String): People

    suspend fun updateIsFavorite(isFavorite: Boolean, name: String)

    suspend fun isUpdate(): Boolean
}
