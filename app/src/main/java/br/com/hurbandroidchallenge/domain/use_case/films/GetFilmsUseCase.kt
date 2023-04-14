package br.com.hurbandroidchallenge.domain.use_case.films

import br.com.hurbandroidchallenge.data.repository.FilmsRepository

class GetFilmsUseCase(
    private val repository: FilmsRepository,
) {
    operator fun invoke(url: String, clearLocalDatasource: Boolean) = repository.getItemList(url, clearLocalDatasource)
}