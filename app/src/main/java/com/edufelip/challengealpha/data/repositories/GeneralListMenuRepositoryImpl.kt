package com.edufelip.challengealpha.data.repositories

import android.content.Context
import com.edufelip.challengealpha.common.mapper.ListMapper
import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSource
import com.edufelip.challengealpha.data.models.GeneralListMenuItemResponse
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import javax.inject.Inject

class GeneralListMenuRepositoryImpl @Inject constructor(
    private val localDataSource: GeneralListMenuLocalDataSource,
    private val generalListMenuItemResponseToEntityMapper: ListMapper<GeneralListMenuItemResponse, GeneralListMenuItem>
) : GeneralListMenuRepository {
    override fun getGeneralListMenu(context: Context): List<GeneralListMenuItem> {
        val menu = localDataSource.getGeneralListMenu(context)
        return generalListMenuItemResponseToEntityMapper.map(menu)
    }
}