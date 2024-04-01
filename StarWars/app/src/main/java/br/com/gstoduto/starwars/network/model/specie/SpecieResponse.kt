package br.com.gstoduto.starwars.network.model.specie

import br.com.gstoduto.starwars.database.entities.SpecieEntity
import br.com.gstoduto.starwars.ui.components.getIndex

data class SpecieResponse(
    val name: String,
    val classification: String,
    val designation: String,
    val average_height: String,
    val skin_colors: String,
    val hair_colors: String,
    val eye_colors: String,
    val average_lifespan: String,
    val language: String,
    val url: String,
    val inMyList: Boolean = false
)

fun SpecieResponse.toSpecieEntity(): SpecieEntity {
    return SpecieEntity(
        id = getIndex(url, "/species/").toLong(),
        name = name,
        classification = classification,
        designation = designation,
        averageHeight = average_height,
        skinColors = skin_colors,
        hairColors = hair_colors,
        eyeColors = eye_colors,
        averageLifespan = average_lifespan,
        language = language,
        url = url,
        inMyList = inMyList
    )
}