package br.com.hurbandroidchallenge.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("planets")
class PlanetEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val url: String,
    val image: String,
    val lastSeen: String?,
    val favorite: Boolean
)