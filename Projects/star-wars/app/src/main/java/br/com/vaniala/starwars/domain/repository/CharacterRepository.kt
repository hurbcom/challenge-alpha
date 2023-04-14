package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.People

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
interface CharacterRepository {

    suspend fun getCharacters(page: Int): ApiResponse<People>
}
