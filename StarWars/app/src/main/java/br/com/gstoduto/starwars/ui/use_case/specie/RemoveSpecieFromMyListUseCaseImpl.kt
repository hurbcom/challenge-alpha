package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.repositories.SpecieRepository
import javax.inject.Inject

class RemoveSpecieFromMyListUseCaseImpl @Inject constructor(
    private val repository: SpecieRepository,
): RemoveSpecieFromMyListUseCase {
    override suspend fun removeSpecieFromMyList(id: String) {
        return repository.removeFromMyList(id)
    }
}