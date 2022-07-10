package com.edufelip.challengealpha.domain.usecases

import com.edufelip.challengealpha.domain.models.Planet
import com.edufelip.challengealpha.domain.models.Specie
import com.edufelip.challengealpha.domain.models.base.PagedList
import com.edufelip.challengealpha.domain.repositories.CategoryListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecieListUseCase @Inject constructor(
    private val repository: CategoryListRepository
) {
    operator fun invoke(search: String? = null, page: Int): Flow<PagedList<Specie>> {
        return repository.getSpecieList(search = search, page = page)
    }
}