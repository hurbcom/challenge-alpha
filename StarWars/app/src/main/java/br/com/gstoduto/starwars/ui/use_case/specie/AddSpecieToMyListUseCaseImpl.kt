package br.com.gstoduto.starwars.ui.use_case.specie

import br.com.gstoduto.starwars.repositories.SpecieRepository
import javax.inject.Inject

class AddSpecieToMyListUseCaseImpl @Inject constructor(
    private val repository: SpecieRepository,
): AddSpecieToMyListUseCase {
    override suspend fun addSpecieToMyList(id: String) {
        return repository.addToMyList(id)
    }
}