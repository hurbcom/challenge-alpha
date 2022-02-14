package com.isranascimento.hotels.models

data class Hotel(
    val id: String,
    val sku: String,
    val name: String,
    val gallery: List<String>,
    val mainImage: String,
    val amenities: List<String>,
    val price: Double,
    val address: Address,
    val starCount: Int
)
