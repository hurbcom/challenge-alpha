package com.edufelip.challengealpha.data.mappers.category_list

import com.edufelip.challengealpha.BuildConfig
import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.base.extractIdFromUrl
import com.edufelip.challengealpha.data.network.models.PlanetResponse
import com.edufelip.challengealpha.domain.models.Planet

class PlanetResponseToEntityMapper : Mapper<PlanetResponse, Planet> {
    override fun map(input: PlanetResponse): Planet =
        with(input) {
            val id = extractIdFromUrl(url)
            Planet(
                id,
                name,
                climate,
                diameter,
                gravity,
                orbitalPeriod,
                population,
                rotationPeriod,
                surfaceWater,
                terrain,
                url,
                films,
                residents,
                "${BuildConfig.BASE_IMAGE_URL}/planets/${id}.jpg"
            )
        }
}