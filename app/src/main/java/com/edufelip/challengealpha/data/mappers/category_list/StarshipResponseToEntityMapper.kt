package com.edufelip.challengealpha.data.mappers.category_list

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.models.StarshipResponse
import com.edufelip.challengealpha.domain.models.Starship

class StarshipResponseToEntityMapper : Mapper<StarshipResponse, Starship> {
    override fun map(input: StarshipResponse): Starship =
        with(input) {
            Starship(
                mglt,
                cargoCapacity,
                consumables,
                costInCredits,
                crew,
                hyperdriveRating,
                length,
                manufacturer,
                maxAtmospheringSpeed,
                model,
                url,
                name,
                starshipClass,
                passengers,
                films,
                pilots
            )
        }
}