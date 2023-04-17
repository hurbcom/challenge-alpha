package br.com.vaniala.starwars.data.local.datasource

import br.com.vaniala.starwars.data.local.dao.FilmDao
import br.com.vaniala.starwars.domain.model.Film
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class LocalFilmDataSourceImpl @Inject constructor(
    private val filmDao: FilmDao,
) : LocalDataSource.Films {
    override suspend fun insertAll(films: List<Film>) =
        filmDao.insertAll(films)

    override fun filmsByTitle(query: String): List<Film> =
        filmDao.filmsByTitle(query)

    override suspend fun updateIsFavorite(isFavorite: Boolean, title: String) =
        filmDao.updateIsFavorite(isFavorite, title)

    override suspend fun lastUpdated(): Long =
        filmDao.lastUpdated()

    override suspend fun getCount(): Int =
        filmDao.getCount()

    override suspend fun getFavorites(): List<Film> =
        filmDao.getFavorites()
}
