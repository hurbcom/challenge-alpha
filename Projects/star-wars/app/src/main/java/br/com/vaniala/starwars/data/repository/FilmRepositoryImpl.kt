package br.com.vaniala.starwars.data.repository

import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.data.remote.datasource.RemoteDataSource
import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.repository.FilmRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class FilmRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDatSource: LocalDataSource.Films,
) : FilmRepository {
    override suspend fun insertAll(films: List<Film>) =
        localDatSource.insertAll(films)

    override suspend fun filmsByTitle(query: String): List<Film> =
        localDatSource.filmsByTitle(query)

    override suspend fun getFilmByTitle(query: String): Film =
        localDatSource.getFilmByTitle(query)

    override suspend fun updateIsFavorite(isFavorite: Boolean, title: String) =
        localDatSource.updateIsFavorite(isFavorite, title)

    override suspend fun getFilms(page: Int): ApiResponse<Film> =
        remoteDataSource.getFilms(page)

    override suspend fun isUpdate(): Boolean =
        localDatSource.isUpdate()
}
