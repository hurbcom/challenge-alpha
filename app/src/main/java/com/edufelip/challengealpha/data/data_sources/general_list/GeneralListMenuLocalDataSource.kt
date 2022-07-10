package com.edufelip.challengealpha.data.data_sources.general_list

import android.content.Context
import com.edufelip.challengealpha.data.network.models.GeneralListMenuItemResponse

interface GeneralListMenuLocalDataSource {
    fun getGeneralListMenu(context: Context): List<GeneralListMenuItemResponse>
}