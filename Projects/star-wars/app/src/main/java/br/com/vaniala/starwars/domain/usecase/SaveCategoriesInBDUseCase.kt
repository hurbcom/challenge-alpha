package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class SaveCategoriesInBDUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(categories: List<Category>) =
        categoryRepository.insertAll(categories)
}
