package com.edufelip.challengealpha.data.repositories

import android.content.Context
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.models.GeneralListMenuItemTypeEnum
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GeneralListMenuFakeRepository : GeneralListMenuRepository{
    override fun getGeneralListMenu(context: Context): Flow<List<GeneralListMenuItem>> {
        return flow {
            emit(listOf(
                GeneralListMenuItem(
                title = "title",
                image = "image",
                resourceUrl = "",
                type = GeneralListMenuItemTypeEnum.FILMS
            )
            ))
        }
    }
}