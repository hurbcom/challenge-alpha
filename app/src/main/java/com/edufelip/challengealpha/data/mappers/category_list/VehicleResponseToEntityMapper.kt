package com.edufelip.challengealpha.data.mappers.category_list

import com.edufelip.challengealpha.BuildConfig
import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.base.extractIdFromUrl
import com.edufelip.challengealpha.data.network.models.VehicleResponse
import com.edufelip.challengealpha.domain.models.Vehicle

class VehicleResponseToEntityMapper : Mapper<VehicleResponse, Vehicle> {
    override fun map(input: VehicleResponse): Vehicle =
        with(input) {
            val id = extractIdFromUrl(url)
            Vehicle(
                id,
                cargoCapacity,
                consumables,
                costInCredits,
                crew,
                length,
                manufacturer,
                maxAtmospheringSpeed,
                model,
                url,
                name,
                passengers,
                vehicleClass,
                films,
                pilots,
                "${BuildConfig.BASE_IMAGE_URL}/vehicles/${id}.jpg"
            )
        }
}