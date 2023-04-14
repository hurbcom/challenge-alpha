package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.data.repository.FilmsRepository
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow

class GetFilmByUrlUseCase(
    private val repository: FilmsRepository
) {
    operator fun invoke(url: String): Flow<Film> {
        return repository.getItemByUrl(url)
    }
}