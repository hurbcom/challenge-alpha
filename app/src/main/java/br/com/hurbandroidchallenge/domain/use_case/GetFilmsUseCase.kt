package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.data.repository.FilmsRepository

class GetFilmsUseCase(
    private val repository: FilmsRepository,
) {
    operator fun invoke(url: String) = repository.getItemList(url)
}