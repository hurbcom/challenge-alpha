package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.data.repository.CharactersRepository
import br.com.hurbandroidchallenge.domain.model.People
import kotlinx.coroutines.flow.Flow

class GetCharacterByUrlUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke(url: String): Flow<People> {
        return repository.getItemByUrl(url)
    }
}