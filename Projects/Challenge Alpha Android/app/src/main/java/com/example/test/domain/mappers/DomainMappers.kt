package com.example.test.domain.mappers

import com.example.core.base.data.BaseResult
import com.example.core.base.presentation.CategoryType
import com.example.test.domain.models.Person
import com.example.test.domain.models.Planet
import com.example.test.domain.models.Starship
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.utils.ImageHelper

object DomainMappers {
    fun Person.map(): CategoryItemDetailsViewData = CategoryItemDetailsViewData(
        type = CategoryType.PEOPLE,
        name = this.name,
        infoOne = this.height,
        infoTwo = this.mass,
        infoThree = this.gender,
        infoFour = this.eyeColor
    )

    fun BaseResult<List<Person>>.mapPeople(): BaseResult<List<CategoryItemDetailsViewData>> =
        when (this) {
            is BaseResult.Success -> BaseResult.Success(
                data = this.data.map { it.map() },
                extraData = this.extraData
            )
            is BaseResult.Error -> this
            else -> BaseResult.Loading
        }

    fun Planet.map(): CategoryItemDetailsViewData = CategoryItemDetailsViewData(
        type = CategoryType.PLANETS,
        name = this.name,
        infoOne = this.climate,
        infoTwo = this.population,
        infoThree = this.terrain,
        infoFour = this.diameter
    )

    fun BaseResult<List<Planet>>.mapPlanets(): BaseResult<List<CategoryItemDetailsViewData>> =
        when (this) {
            is BaseResult.Success -> BaseResult.Success(
                data = this.data.map { it.map() },
                extraData = this.extraData
            )
            is BaseResult.Error -> this
            else -> BaseResult.Loading
        }

    fun Starship.map(): CategoryItemDetailsViewData = CategoryItemDetailsViewData(
        type = CategoryType.STARSHIPS,
        name = this.name,
        infoOne = this.starshipClass,
        infoTwo = this.passengers,
        infoThree = this.manufacturer,
        infoFour = this.cargoCapacity
    )

    fun BaseResult<List<Starship>>.mapStarships(): BaseResult<List<CategoryItemDetailsViewData>> =
        when (this) {
            is BaseResult.Success -> BaseResult.Success(
                data = this.data.map { it.map() },
                extraData = this.extraData
            )
            is BaseResult.Error -> this
            else -> BaseResult.Loading
        }
}