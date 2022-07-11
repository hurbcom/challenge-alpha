package com.edufelip.challengealpha.data.mappers.category_list

import com.edufelip.challengealpha.BuildConfig
import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.base.extractIdFromUrl
import com.edufelip.challengealpha.data.network.models.SpecieResponse
import com.edufelip.challengealpha.domain.models.Specie

class SpecieResponseToEntityMapper : Mapper<SpecieResponse, Specie> {
    override fun map(input: SpecieResponse): Specie =
        with(input) {
            val id = extractIdFromUrl(url)
            Specie(
                id = id,
                averageHeight = averageHeight,
                averageLifespan = averageLifespan,
                classification = classification,
                designation = designation,
                eyeColors = eyeColors,
                homeworld = homeworld,
                language = language,
                name = name,
                skinColors = skinColors,
                url = url,
                people = people,
                films = films,
                imageUrl = "${BuildConfig.BASE_IMAGE_URL}/species/${id}.jpg"
            )
        }
}