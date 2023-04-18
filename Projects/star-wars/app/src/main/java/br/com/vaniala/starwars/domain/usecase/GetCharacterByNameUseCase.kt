package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class GetCharacterByNameUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    operator fun invoke(query: String): Flow<People> = flow {
        emit(characterRepository.getCharacterByName(query))
    }.catch { e ->
        e.printStackTrace()
    }.flowOn(Dispatchers.IO)
}
