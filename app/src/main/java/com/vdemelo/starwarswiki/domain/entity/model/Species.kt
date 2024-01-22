package com.vdemelo.starwarswiki.domain.entity.model

import com.vdemelo.starwarswiki.data.remote.response.SpeciesResponse
import com.vdemelo.starwarswiki.utils.nonNullOrEmpty

class Species(
    val averageHeight: String?,
    val averageLifespan: String?,
    val classification: String?,
    val created: String?,
    val designation: String?,
    val edited: String?,
    val eyeColors: String?,
    val films: List<String>,
    val hairColors: String?,
    val homeWorld: String?,
    val language: String?,
    val name: String?,
    val people: List<String>,
    val skinColors: String?,
    val url: String?
) {

    constructor(response: SpeciesResponse) : this(
        averageHeight = response.averageHeight,
        averageLifespan = response.averageLifespan,
        classification = response.classification,
        created = response.created,
        designation = response.designation,
        edited = response.edited,
        eyeColors = response.eyeColors,
        films = response.films.nonNullOrEmpty(),
        hairColors = response.hairColors,
        homeWorld = response.homeWorld,
        language = response.language,
        name = response.name,
        people = response.people.nonNullOrEmpty(),
        skinColors = response.skinColors,
        url = response.url
    )

}

fun List<SpeciesResponse>.toModel(): List<Species> = this.map { Species(it) }
