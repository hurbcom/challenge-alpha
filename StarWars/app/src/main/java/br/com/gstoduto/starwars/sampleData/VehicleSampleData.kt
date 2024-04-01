package br.com.gstoduto.starwars.sampleData

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.gstoduto.starwars.model.Vehicle
import kotlin.random.Random

val randomImageVehicle
    get() = "https://swapi.dev/api/vehicles/${Random.nextInt(1, 6)}/${
        Random.nextInt(
            2,
            4
        )
    }"

val randomName
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomModel
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomManufacturer
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomVehicleClass
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomCostInCredits
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomMaxAtmospheringSpeed
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomLength
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomPassengers
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()

val sampleVehicles = List(15) {
    Vehicle(
        name = randomName,
        model = randomModel,
        manufacturer = randomManufacturer,
        vehicleClass = randomVehicleClass,
        costInCredits = randomCostInCredits,
        maxAtmospheringSpeed = randomMaxAtmospheringSpeed,
        length = randomLength,
        passengers = randomPassengers,
        url = randomImageVehicle,
        inMyList = true
    )
}

val sampleVehicleAdded =
    Vehicle(
        name = randomName,
        model = randomModel,
        manufacturer = randomManufacturer,
        vehicleClass = randomVehicleClass,
        costInCredits = randomCostInCredits,
        maxAtmospheringSpeed = randomMaxAtmospheringSpeed,
        length = randomLength,
        passengers = randomPassengers,
        url = randomImageVehicle,
        inMyList = false
    )

val sampleVehicleRemoved =
    Vehicle(
        name = randomName,
        model = randomModel,
        manufacturer = randomManufacturer,
        vehicleClass = randomVehicleClass,
        costInCredits = randomCostInCredits,
        maxAtmospheringSpeed = randomMaxAtmospheringSpeed,
        length = randomLength,
        passengers = randomPassengers,
        url = randomImageVehicle,
        inMyList = true
    )
