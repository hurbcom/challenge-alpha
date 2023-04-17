package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.LastSeen

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
interface LastSeenRepository {
    suspend fun getLastSeen(): List<LastSeen>

    suspend fun insert(lastSeen: LastSeen)
}
