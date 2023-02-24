package com.example.test.mocks

import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.presentation.models.CategoryType

object ViewModelMocks {
    val people = listOf(
        CategoryItemDetailsViewData(
            name = "Eduardo",
            image = "http://imageurl.com",
            infoOne = "177",
            infoTwo = "88",
            infoThree = "male",
            infoFour = "black",
            type = CategoryType.PEOPLE
        )
    )
}