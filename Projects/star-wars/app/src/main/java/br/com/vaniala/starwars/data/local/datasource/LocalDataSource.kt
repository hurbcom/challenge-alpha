package br.com.vaniala.starwars.data.local.datasource

import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.model.Favorite
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.model.People

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
interface LocalDataSource {

    interface Categories {
        suspend fun insertAll(categories: List<Category>)

        fun getAll(): List<Category>
    }

    interface Films {
        suspend fun insertAll(films: List<Film>)

        fun filmsByTitle(query: String): List<Film>

        suspend fun updateIsFavorite(isFavorite: Boolean, title: String)

        suspend fun lastUpdated(): Long

        suspend fun getCount(): Int

        suspend fun getFavorites(): List<Film>
    }

    interface Characters {

        suspend fun insertAll(characters: List<People>)

        suspend fun characterByName(query: String): List<People>

        suspend fun updateIsFavorite(isFavorite: Boolean, name: String)

        suspend fun lastUpdated(): Long

        suspend fun getCount(): Int

        suspend fun getFavorites(): List<People>
    }

    interface Favorites {
        suspend fun getFavorites(): Favorite
    }
}
