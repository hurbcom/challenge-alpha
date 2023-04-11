package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository

class GetCharactersUseCase(
    private val repository: StarWarsBookRepository,
) {
    operator fun invoke(url: String) = repository.getCharacters(url = url)
}