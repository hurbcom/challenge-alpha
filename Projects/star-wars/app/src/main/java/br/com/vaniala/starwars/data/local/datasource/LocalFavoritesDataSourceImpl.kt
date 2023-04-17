package br.com.vaniala.starwars.data.local.datasource

import br.com.vaniala.starwars.data.local.dao.CharacterDao
import br.com.vaniala.starwars.data.local.dao.FilmDao
import br.com.vaniala.starwars.domain.model.Favorite
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class LocalFavoritesDataSourceImpl @Inject constructor(
    private val filmDao: FilmDao,
    private val characterDao: CharacterDao,
) : LocalDataSource.Favorites {

    override suspend fun getFavorites(): Favorite {
        val films = filmDao.getFavorites()
        val characters = characterDao.getFavorites()
        return Favorite(films, characters)
    }
}
