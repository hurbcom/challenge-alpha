package com.edufelip.challengealpha.data.mappers.category_list

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.models.SpecieResponse
import com.edufelip.challengealpha.domain.models.Specie

class SpecieResponseToEntityMapper : Mapper<SpecieResponse, Specie> {
    override fun map(input: SpecieResponse): Specie =
        with(input) {
            Specie(
                averageHeight,
                averageLifespan,
                classification,
                designation,
                eyeColors,
                homeworld,
                language,
                name,
                skinColors,
                url,
                people,
                films
            )
        }
}