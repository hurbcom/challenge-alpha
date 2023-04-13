package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow

class GetFilmByUrlUseCase(
    private val repository: StarWarsBookRepository
) {
    operator fun invoke(url: String, fromRemote: Boolean): Flow<Film> {
        return repository.getFilmByUrl(url, fromRemote)
    }
}