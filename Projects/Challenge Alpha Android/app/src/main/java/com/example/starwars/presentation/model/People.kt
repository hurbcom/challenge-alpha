package com.example.starwars.presentation.model

import com.example.starwars.data.model.PeopleRemote
import com.example.starwars.presentation.ext.prepareUrl
import java.io.Serializable

data class People(
    val image: String,
    val name: String,
    val height: String,
    val mass: String,
    val eyesColor: String,
    val birthYear: String
) : Serializable, Item(name)

fun List<PeopleRemote>.toListPeople(): List<People> {
    val peopleList = mutableListOf<People>()
    this.forEach {
        peopleList.add(
            People(
                image = it.image.prepareUrl("characters"),
                name = it.name,
                height = it.height + "cm",
                mass = "${it.mass} Kg",
                eyesColor = it.eyeColor,
                birthYear = it.birthYear
            )
        )
    }
    return peopleList
}

