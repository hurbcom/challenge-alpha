package com.edufelip.challengealpha.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(
    val id: String,
    val name: String,
    val climate: String,
    val diameter: String,
    val gravity: String,
    val orbitalPeriod: String,
    val population: String,
    val rotationPeriod: String,
    val surfaceWater: String,
    val terrain: String,
    val url: String,
    val films: List<String>,
    val residents: List<String>,
    val imageUrl: String,
) : Parcelable