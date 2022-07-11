package com.edufelip.challengealpha.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Specie(
    val id: String,
    val averageHeight: String,
    val averageLifespan: String,
    val classification: String,
    val designation: String,
    val eyeColors: String,
    val homeworld: String?,
    val language: String,
    val name: String,
    val skinColors: String,
    val url: String,
    val people: List<String>,
    val films: List<String>,
    val imageUrl: String,
) : Parcelable