package com.edufelip.challengealpha.domain.usecases

import android.content.Context
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import javax.inject.Inject

class GetGeneralListMenuItemsUseCase @Inject constructor(
    private val repository: GeneralListMenuRepository
) {
    operator fun invoke(context: Context): List<GeneralListMenuItem> {
        return repository.getGeneralListMenu(context)
    }
}