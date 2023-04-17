package br.com.hurbandroidchallenge.domain.use_case.characters

import br.com.hurbandroidchallenge.data.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke(url: String, clearLocalDataSource: Boolean = false) =
        repository.getItemList(url, clearLocalDataSource)
}