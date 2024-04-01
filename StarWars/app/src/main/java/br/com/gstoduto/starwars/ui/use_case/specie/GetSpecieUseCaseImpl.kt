package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.database.entities.SpecieEntity
import br.com.gstoduto.starwars.repositories.SpecieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecieUseCaseImpl @Inject constructor(
    private val repository: SpecieRepository,
): GetSpecieUseCase {
    override suspend fun findSpecie(id: String): Flow<SpecieEntity?> {
        return repository.findSpecie(id)
    }
}