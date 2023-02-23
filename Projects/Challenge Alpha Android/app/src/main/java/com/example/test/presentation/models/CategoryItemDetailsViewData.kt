package com.example.test.presentation.models

import com.example.core.base.presentation.BaseViewData
import com.example.core.base.presentation.CategoryType

data class CategoryItemDetailsViewData(
    val type: CategoryType,
    val name: String,
    val infoOne: String,
    val infoTwo: String,
    val infoThree: String,
    val infoFour: String
) : BaseViewData()
