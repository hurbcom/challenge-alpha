package br.com.gstoduto.starwars.network.model.vehicle

import br.com.gstoduto.starwars.database.entities.VehicleEntity
import br.com.gstoduto.starwars.ui.components.getIndex

data class VehicleResponse(
    val name: String,
    val model: String,
    val manufacturer: String,
    val vehicle_class: String,
    val cost_in_credits: String,
    val max_atmosphering_speed: String,
    val length: String,
    val cargo_capacity: String,
    val consumables: String,
    val passengers: String,
    val url: String,
    val inMyList: Boolean
)

fun VehicleResponse.toVehicleEntity(): VehicleEntity {
    return VehicleEntity(
        id = getIndex(url, "/vehicles/").toLong(),
        name = name,
        model = model,
        manufacturer = manufacturer,
        vehicleClass = vehicle_class,
        costInCredits = cost_in_credits,
        maxAtmospheringSpeed = max_atmosphering_speed,
        length = length,
        cargoCapacity = cargo_capacity,
        consumables = consumables,
        passengers = passengers,
        url = url,
        inMyList = inMyList
    )
}