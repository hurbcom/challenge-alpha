package br.com.hurbandroidchallenge.domain.use_case.films

import br.com.hurbandroidchallenge.data.repository.FilmsRepository
import br.com.hurbandroidchallenge.domain.model.Film
import kotlinx.coroutines.flow.Flow

class SetFavoriteFilmUseCase(
    private val repository: FilmsRepository
) {
    operator fun invoke(film: Film): Flow<Unit> {
        return repository.updateItem(film.copy(favorite = !film.favorite))
    }
}