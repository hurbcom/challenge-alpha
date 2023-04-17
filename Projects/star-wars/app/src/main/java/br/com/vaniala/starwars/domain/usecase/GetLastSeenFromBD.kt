package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.domain.model.LastSeen
import br.com.vaniala.starwars.domain.repository.LastSeenRepository
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
class GetLastSeenFromBD @Inject constructor(
    private val lastSeenRepository: LastSeenRepository,
) {
    operator fun invoke(): Flow<State<List<LastSeen>>> = flow {
        val lastSeen = lastSeenRepository.getLastSeen()
        emit(State.Success(lastSeen))
    }.catch { e ->
        e.printStackTrace()
    }.flowOn(Dispatchers.IO)
}
