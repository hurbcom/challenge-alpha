package br.com.vaniala.starwars.data.repository

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
    private val dataSource: RemoteDataSource,
) : FilmRepository {
    override suspend fun getFilms(page: Int): ApiResponse<Film> =
        dataSource.getFilms(page)
}
