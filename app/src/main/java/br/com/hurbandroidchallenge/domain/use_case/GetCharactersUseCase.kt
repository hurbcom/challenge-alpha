package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.data.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke(url: String) = repository.getItemList(url)
}