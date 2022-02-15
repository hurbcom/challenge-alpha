package com.isranascimento.hotels.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hotel(
    val id: String,
    val sku: String,
    val name: String,
    val gallery: List<String>,
    val mainImage: String,
    val amenities: List<String>,
    val price: Double,
    val address: Address,
    val starCount: Int,
    val description: String,
    val url: String
): Parcelable
