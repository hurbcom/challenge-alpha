package br.com.gstoduto.starwars.sampleData

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.gstoduto.starwars.model.Specie
import kotlin.random.Random

val randomImageSpecie
    get() = "https://swapi.dev/api/species/${Random.nextInt(1, 6)}/${
        Random.nextInt(
            2,
            4
        )
    }"

val randomNameSpecie
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomClassification
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomDesignation
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomAverageHeight
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomSkinColors
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomHairColors
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomEyeColors
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomAverageLifespan
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomLanguage
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()

val sampleSpecies = List(15) {
    Specie(
        name = randomNameSpecie,
        classification = randomClassification,
        designation = randomDesignation,
        averageHeight = randomAverageHeight,
        skinColors = randomSkinColors,
        hairColors = randomHairColors,
        eyeColors = randomEyeColors,
        averageLifespan = randomAverageLifespan,
        language = randomLanguage,
        url = randomImageSpecie,
        inMyList = true
    )
}

val sampleSpecieAdded =
    Specie(
        name = randomNameSpecie,
        classification = randomClassification,
        designation = randomDesignation,
        averageHeight = randomAverageHeight,
        skinColors = randomSkinColors,
        hairColors = randomHairColors,
        eyeColors = randomEyeColors,
        averageLifespan = randomAverageLifespan,
        language = randomLanguage,
        url = randomImageVehicle,
        inMyList = false
    )

val sampleSpecieRemoved =
    Specie(
        name = randomNameSpecie,
        classification = randomClassification,
        designation = randomDesignation,
        averageHeight = randomAverageHeight,
        skinColors = randomSkinColors,
        hairColors = randomHairColors,
        eyeColors = randomEyeColors,
        averageLifespan = randomAverageLifespan,
        language = randomLanguage,
        url = randomImageVehicle,
        inMyList = true
    )
