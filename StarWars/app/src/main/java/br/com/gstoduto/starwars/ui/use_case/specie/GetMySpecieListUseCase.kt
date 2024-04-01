package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.database.entities.SpecieEntity
import kotlinx.coroutines.flow.Flow

interface GetMySpecieListUseCase {
    suspend fun getMyListSpecies(): Flow<List<SpecieEntity>>
}