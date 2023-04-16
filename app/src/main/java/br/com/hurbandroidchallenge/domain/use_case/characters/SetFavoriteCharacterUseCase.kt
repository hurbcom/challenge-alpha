package br.com.hurbandroidchallenge.domain.use_case.characters

import br.com.hurbandroidchallenge.data.repository.CharactersRepository
import br.com.hurbandroidchallenge.domain.model.People
import kotlinx.coroutines.flow.Flow

class SetFavoriteCharacterUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(people: People): Flow<People> {
        return repository.updateItem(people.copy(favorite = !people.favorite))
    }
}