package br.com.vaniala.starwars.data.local.datasource

import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.model.Favorite
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.model.LastSeen
import br.com.vaniala.starwars.domain.model.People

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
interface LocalDataSource {

    interface Categories {
        suspend fun insertAll(categories: List<Category>)

        suspend fun getAll(): List<Category>

        suspend fun isUpdate(): Boolean
    }

    interface Films {
        suspend fun insertAll(films: List<Film>)

        suspend fun filmsByTitle(query: String): List<Film>

        suspend fun getFilmByTitle(query: String): Film

        suspend fun updateIsFavorite(isFavorite: Boolean, title: String)

        suspend fun getFavorites(): List<Film>

        suspend fun isUpdate(): Boolean
    }

    interface Characters {

        suspend fun insertAll(characters: List<People>)

        suspend fun charactersByName(query: String): List<People>

        suspend fun getCharacterByName(query: String): People

        suspend fun updateIsFavorite(isFavorite: Boolean, name: String)

        suspend fun getFavorites(): List<People>
        suspend fun isUpdate(): Boolean
    }

    interface Favorites {
        suspend fun getFavorites(): Favorite
    }

    interface LastSeenI {
        suspend fun getLastSeen(): List<LastSeen>
        suspend fun insert(lastSeen: LastSeen)
    }
}
