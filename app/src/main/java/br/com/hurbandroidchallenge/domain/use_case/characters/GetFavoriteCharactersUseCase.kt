package br.com.hurbandroidchallenge.domain.use_case.characters

import br.com.hurbandroidchallenge.data.repository.CharactersRepository

class GetFavoriteCharactersUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke() = repository.getFavoriteItems()
}