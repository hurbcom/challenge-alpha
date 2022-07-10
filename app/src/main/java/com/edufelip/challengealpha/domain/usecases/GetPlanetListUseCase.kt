package com.edufelip.challengealpha.domain.usecases

import com.edufelip.challengealpha.domain.models.Planet
import com.edufelip.challengealpha.domain.models.base.PagedList
import com.edufelip.challengealpha.domain.repositories.CategoryListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetListUseCase @Inject constructor(
    private val repository: CategoryListRepository
) {
    operator fun invoke(search: String? = null, page: Int): Flow<PagedList<Planet>> {
        return repository.getPlanetList(search = search, page = page)
    }
}