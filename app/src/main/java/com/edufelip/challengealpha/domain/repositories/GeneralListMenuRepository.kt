package com.edufelip.challengealpha.domain.repositories

import android.content.Context
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem

interface GeneralListMenuRepository {
    fun getGeneralListMenu(context: Context): List<GeneralListMenuItem>
}