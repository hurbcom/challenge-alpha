package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterByUrlUseCase(
    private val repository: StarWarsBookRepository
) {
    operator fun invoke(url: String): Flow<People> {
        return repository.getCharacterById(url)
    }
}