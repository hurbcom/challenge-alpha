package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.domain.model.LastSeen
import br.com.vaniala.starwars.domain.repository.LastSeenRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class SaveLastSeenInBD @Inject constructor(
    private val lastSeenRepository: LastSeenRepository,
) {
    suspend operator fun invoke(lastSeen: LastSeen) =
        lastSeenRepository.insert(lastSeen)
}
