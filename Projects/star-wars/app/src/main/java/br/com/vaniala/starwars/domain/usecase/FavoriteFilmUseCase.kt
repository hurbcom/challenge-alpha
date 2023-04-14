package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.domain.model.Film
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class FavoriteFilmUseCase @Inject constructor(
    private val localFilmDataSourceImpl: LocalDataSource.Films,
) {

    suspend operator fun invoke(film: Film) =
        film.title.let {
            localFilmDataSourceImpl.updateIsFavorite(isFavorite = film.isFavorite, title = it)
        }
}
