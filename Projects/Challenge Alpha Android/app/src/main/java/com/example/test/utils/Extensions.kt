package com.example.test.utils

import android.content.Context
import com.example.core.base.presentation.BaseExtensions.capitalizeWords
import com.example.core.base.presentation.CategoryType
import com.example.test.R
import com.example.test.presentation.models.CategoryItemDetailsViewData

object Extensions {
    fun CategoryItemDetailsViewData.withHelper(context: Context): CategoryItemDetailsViewData {
        return when (type) {
            CategoryType.PEOPLE -> CategoryItemDetailsViewData(
                type = this.type,
                name = this.name.capitalizeWords(),
                infoOne = "${context.getString(R.string.height)}: $infoOne cm".capitalizeWords(),
                infoTwo = "${context.getString(R.string.weight)}: $infoTwo kg".capitalizeWords(),
                infoThree = "${context.getString(R.string.gender)}: $infoThree".capitalizeWords(),
                infoFour = "${context.getString(R.string.eye_color)}: $infoFour".capitalizeWords()
            )

            CategoryType.PLANETS -> CategoryItemDetailsViewData(
                type = this.type,
                name = this.name.capitalizeWords(),
                infoOne = "${context.getString(R.string.climate)}: $infoOne".capitalizeWords(),
                infoTwo = "${context.getString(R.string.population)}: $infoTwo".capitalizeWords(),
                infoThree = "${context.getString(R.string.terrain)}: $infoThree".capitalizeWords(),
                infoFour = "${context.getString(R.string.diameter)}: $infoFour".capitalizeWords()
            )

            else -> CategoryItemDetailsViewData(
                type = this.type,
                name = this.name.capitalizeWords(),
                infoOne = "${context.getString(R.string.type)}: $infoOne".capitalizeWords(),
                infoTwo = "${context.getString(R.string.passengers)}: $infoTwo".capitalizeWords(),
                infoThree = "${context.getString(R.string.manufacturer)}: $infoThree".capitalizeWords(),
                infoFour = "${context.getString(R.string.capacity)}: $infoFour".capitalizeWords()
            )
        }
    }
}