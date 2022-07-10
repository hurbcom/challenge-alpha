package com.edufelip.challengealpha.data.mappers

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.models.PeopleResponse
import com.edufelip.challengealpha.domain.models.People

class PeopleResponseToEntityMapper : Mapper<PeopleResponse, People> {
    override fun map(input: PeopleResponse): People =
        with(input) {
            People(
                birthdayYear,
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
                vehicles
            )
        }
}