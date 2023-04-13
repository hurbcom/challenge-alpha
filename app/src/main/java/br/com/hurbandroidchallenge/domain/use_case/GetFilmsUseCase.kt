package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository

class GetFilmsUseCase(
    private val repository: StarWarsBookRepository,
) {
    operator fun invoke(url: String) = repository.getFilms(url)
}