package com.edufelip.challengealpha.domain.usecases

import com.edufelip.challengealpha.domain.models.Film
import com.edufelip.challengealpha.domain.models.base.PagedList
import com.edufelip.challengealpha.domain.repositories.CategoryListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmListUseCase @Inject constructor(
    private val repository: CategoryListRepository
) {
    operator fun invoke(search: String? = null, page: Int = 1): Flow<PagedList<Film>> {
        return repository.getFilmList(search = search, page = page)
    }
}