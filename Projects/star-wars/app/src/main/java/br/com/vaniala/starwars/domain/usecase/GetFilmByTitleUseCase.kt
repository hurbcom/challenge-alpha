package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.repository.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class GetFilmByTitleUseCase @Inject constructor(
    private val filmRepository: FilmRepository,
) {
    operator fun invoke(query: String): Flow<Film> = flow {
        emit(filmRepository.getFilmByTitle(query))
    }.catch { e ->
        e.printStackTrace()
    }.flowOn(Dispatchers.IO)
}
