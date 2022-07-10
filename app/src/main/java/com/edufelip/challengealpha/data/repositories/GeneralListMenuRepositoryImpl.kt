package com.edufelip.challengealpha.data.repositories

import android.content.Context
import com.edufelip.challengealpha.common.mapper.ListMapper
import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSource
import com.edufelip.challengealpha.data.models.GeneralListMenuItemResponse
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GeneralListMenuRepositoryImpl @Inject constructor(
    private val localDataSource: GeneralListMenuLocalDataSource,
    private val generalListMenuItemResponseToEntityMapper: ListMapper<GeneralListMenuItemResponse, GeneralListMenuItem>
) : GeneralListMenuRepository {
    override fun getGeneralListMenu(context: Context): Flow<List<GeneralListMenuItem>> {
        return flow {
            val menu = localDataSource.getGeneralListMenu(context)
            val mappedMenu = generalListMenuItemResponseToEntityMapper.map(menu)
            emit(mappedMenu)
        }.flowOn(Dispatchers.IO)
    }
}