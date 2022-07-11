package com.edufelip.challengealpha.data.mappers.category_list

import com.edufelip.challengealpha.BuildConfig
import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.base.extractIdFromUrl
import com.edufelip.challengealpha.data.network.models.PeopleResponse
import com.edufelip.challengealpha.domain.models.People

class PeopleResponseToEntityMapper : Mapper<PeopleResponse, People> {
    override fun map(input: PeopleResponse): People =
        with(input) {
            val id = extractIdFromUrl(url)
            People(
                id,
                birthYear,
                eyeColor,
                films,
                gender,
                hairColor,
                height,
                homeworldUrl,
                mass,
                name,
                skinColor,
                species,
                starships,
                url,
                vehicles,
                "${BuildConfig.BASE_IMAGE_URL}/characters/${id}.jpg"
            )
        }
}