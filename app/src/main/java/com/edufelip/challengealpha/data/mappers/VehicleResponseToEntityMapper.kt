package com.edufelip.challengealpha.data.mappers

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.models.VehicleResponse
import com.edufelip.challengealpha.domain.models.Vehicle

class VehicleResponseToEntityMapper : Mapper<VehicleResponse, Vehicle> {
    override fun map(input: VehicleResponse): Vehicle =
        with(input) {
            Vehicle(
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
                pilots
            )
        }
}