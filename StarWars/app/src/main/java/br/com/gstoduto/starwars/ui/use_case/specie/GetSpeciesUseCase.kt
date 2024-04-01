package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.model.Specie
import kotlinx.coroutines.flow.Flow

interface GetSpeciesUseCase {
    suspend fun findSpecies(): Flow<List<Specie>>
}