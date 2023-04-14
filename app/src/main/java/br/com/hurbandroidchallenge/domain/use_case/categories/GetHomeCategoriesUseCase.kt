package br.com.hurbandroidchallenge.domain.use_case.categories

import br.com.hurbandroidchallenge.data.repository.CategoriesRepository

class GetHomeCategoriesUseCase(
    private val repository: CategoriesRepository,
) {
    operator fun invoke(clearLocalDatasource: Boolean) = repository.getCategories(clearLocalDatasource)
}