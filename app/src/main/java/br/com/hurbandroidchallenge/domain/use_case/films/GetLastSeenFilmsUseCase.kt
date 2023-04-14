package br.com.hurbandroidchallenge.domain.use_case.films

import br.com.hurbandroidchallenge.data.repository.FilmsRepository

class GetLastSeenFilmsUseCase(
    private val repository: FilmsRepository,
) {
    operator fun invoke() = repository.getLastSeenItems()
}