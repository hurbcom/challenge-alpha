package com.edufelip.challengealpha.domain.usecases

import android.content.Context
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGeneralListMenuItemsUseCase @Inject constructor(
    private val repository: GeneralListMenuRepository
) {
    operator fun invoke(context: Context): Flow<List<GeneralListMenuItem>> {
        return repository.getGeneralListMenu(context)
    }
}