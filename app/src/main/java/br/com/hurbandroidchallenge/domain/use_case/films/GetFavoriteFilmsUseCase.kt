package br.com.hurbandroidchallenge.domain.use_case.films

import br.com.hurbandroidchallenge.data.repository.FilmsRepository

class GetFavoriteFilmsUseCase(
    private val repository: FilmsRepository,
) {
    operator fun invoke() = repository.getFavoriteItems()
}