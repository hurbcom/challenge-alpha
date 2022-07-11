package com.edufelip.challengealpha.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Starship(
    val id: String,
    val mglt: String,
    val cargoCapacity: String,
    val consumables: String,
    val costInCredits: String,
    val crew: String,
    val hyperdriveRating: String,
    val length: String,
    val manufacturer: String,
    val maxAtmospheringSpeed: String,
    val model: String,
    val url: String,
    val name: String,
    val starshipClass: String,
    val passengers: String,
    val films: List<String>,
    val pilots: List<String>,
    val imageUrl: String,
) : Parcelable