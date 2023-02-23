package com.example.test.presentation.models

import com.example.core.base.presentation.BaseViewData

data class CategoryItemDetailsViewData(
    val type: CategoryType,
    val image: String,
    val name: String,
    val infoOne: String,
    val infoTwo: String,
    val infoThree: String,
    val infoFour: String
) : BaseViewData()

enum class CategoryType {
    PEOPLE, PLANETS, STARSHIPS
}
