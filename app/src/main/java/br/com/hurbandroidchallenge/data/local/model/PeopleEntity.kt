package br.com.hurbandroidchallenge.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("people")
class PeopleEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val birthYear: String,
    val gender: String,
    val homeWorld: String,
    val url: String,
    val films: List<String>,
    val vehicles: List<String>,
    val starships: List<String>
)