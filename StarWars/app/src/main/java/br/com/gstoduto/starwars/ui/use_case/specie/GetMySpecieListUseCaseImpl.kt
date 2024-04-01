package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.database.entities.SpecieEntity
import br.com.gstoduto.starwars.repositories.SpecieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMySpecieListUseCaseImpl @Inject constructor(
    private val repository: SpecieRepository,
): GetMySpecieListUseCase {
    override suspend fun getMyListSpecies(): Flow<List<SpecieEntity>> {
        return repository.myList()
    }
}