package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.data.repository.CategoriesRepository

class GetHomeCategoriesUseCase(
    private val repository: CategoriesRepository,
) {
    operator fun invoke(clearLocalDatasource: Boolean) = repository.getItemList("", clearLocalDatasource)
}