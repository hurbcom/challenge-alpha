package com.example.test.data.mappers

import com.example.test.data.datasources.network.models.*
import com.example.test.data.datasources.network.models.people.PersonResponse
import com.example.test.domain.models.*

object DataMapper {
    fun PersonResponse.map(): Person = Person(
        name = name.orEmpty(),
        height = height.orEmpty(),
        mass = mass.orEmpty(),
        gender = gender.orEmpty(),
        eyeColor = eyeColor.orEmpty()
    )
}