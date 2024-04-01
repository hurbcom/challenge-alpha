package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.repositories.SpecieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpeciesUseCaseImpl @Inject constructor(
    private val repository: SpecieRepository,
): GetSpeciesUseCase {
    override suspend fun findSpecies(): Flow<List<Specie>> {
        return repository.findSpecies()
    }
}