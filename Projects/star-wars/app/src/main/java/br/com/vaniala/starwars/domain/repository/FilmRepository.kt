package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.Film

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
interface FilmRepository {

    suspend fun getFilms(page: Int): ApiResponse<Film>
}
