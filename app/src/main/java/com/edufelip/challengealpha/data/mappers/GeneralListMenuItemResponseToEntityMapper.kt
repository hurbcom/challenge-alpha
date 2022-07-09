package com.edufelip.challengealpha.data.mappers

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.models.GeneralListMenuItemResponse
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem

class GeneralListMenuItemResponseToEntityMapper :
    Mapper<GeneralListMenuItemResponse, GeneralListMenuItem> {
    override fun map(input: GeneralListMenuItemResponse): GeneralListMenuItem =
        with(input) {
            GeneralListMenuItem(
                title = title,
                image = image,
                resourceUri = resourceUri
            )
        }
}