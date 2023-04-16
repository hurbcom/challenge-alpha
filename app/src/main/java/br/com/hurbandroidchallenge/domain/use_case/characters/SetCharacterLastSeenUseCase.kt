package br.com.hurbandroidchallenge.domain.use_case.characters

import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.repository.CharactersRepository
import br.com.hurbandroidchallenge.domain.model.People
import kotlinx.coroutines.flow.Flow

class SetCharacterLastSeenUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(people: People): Flow<People> {
        val now = DateUtils.getCurrentDate()
        return repository.updateItem(people.copy(lastSeen = now))
    }
}