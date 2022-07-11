package com.edufelip.challengealpha.domain.usecases

import com.edufelip.challengealpha.domain.models.Starship
import com.edufelip.challengealpha.domain.models.base.PagedList
import com.edufelip.challengealpha.domain.repositories.CategoryListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStarshipListUseCase @Inject constructor(
    private val repository: CategoryListRepository
) {
    operator fun invoke(search: String? = null, page: Int = 1): Flow<PagedList<Starship>> {
        return repository.getStarshipList(search = search, page = page)
    }
}