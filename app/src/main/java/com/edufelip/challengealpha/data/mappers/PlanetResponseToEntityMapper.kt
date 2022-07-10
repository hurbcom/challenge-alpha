package com.edufelip.challengealpha.data.mappers

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.models.PlanetResponse
import com.edufelip.challengealpha.domain.models.Planet

class PlanetResponseToEntityMapper : Mapper<PlanetResponse, Planet> {
    override fun map(input: PlanetResponse): Planet =
        with(input) {
            Planet(
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
                residents
            )
        }
}