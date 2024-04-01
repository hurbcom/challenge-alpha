package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.database.entities.SpecieEntity
import kotlinx.coroutines.flow.Flow

interface GetSpecieUseCase {
    suspend fun findSpecie(id: String): Flow<SpecieEntity?>
}