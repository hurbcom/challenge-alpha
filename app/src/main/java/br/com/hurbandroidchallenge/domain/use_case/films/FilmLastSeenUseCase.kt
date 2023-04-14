package br.com.hurbandroidchallenge.domain.use_case.films

import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.repository.CharactersRepository
import br.com.hurbandroidchallenge.data.repository.FilmsRepository
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import kotlinx.coroutines.flow.Flow

class FilmLastSeenUseCase(
    private val repository: FilmsRepository
) {
    operator fun invoke(film: Film): Flow<Unit> {
        val now = DateUtils.getCurrentDate()
        return repository.updateItem(film.copy(lastSeen = now))
    }
}