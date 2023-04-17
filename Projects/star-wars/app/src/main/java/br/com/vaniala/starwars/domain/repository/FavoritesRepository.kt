package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.Favorite

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
interface FavoritesRepository {

    suspend fun getFavorites(): Favorite
}
