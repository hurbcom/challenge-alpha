package com.edufelip.challengealpha.domain.repositories

import android.content.Context
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import kotlinx.coroutines.flow.Flow

interface GeneralListMenuRepository {
    fun getGeneralListMenu(context: Context): Flow<List<GeneralListMenuItem>>
}