package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesFromBDUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(): List<Category> =
        categoryRepository.getAll()
}
