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

    override suspend fun filmsByTitle(query: String): List<Film> =
        filmDao.filmsByTitle(query)

    override suspend fun getFilmByTitle(query: String): Film =
        filmDao.getFilmByTitle(query)

    override suspend fun updateIsFavorite(isFavorite: Boolean, title: String) =
        filmDao.updateIsFavorite(isFavorite, title)

    override suspend fun getFavorites(): List<Film> =
        filmDao.getFavorites()

    override suspend fun isUpdate(): Boolean =
        filmDao.isUpdate()
}
