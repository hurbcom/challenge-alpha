package com.filipeoliveira.hurbchallenge.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HotelDB(
    @PrimaryKey val id: String,
    val smallDescription: String,
    val amenities: List<String>,
    val priceValue: String,
    val priceCurrency: String,
    val image: String,
    val name: String,
    val url: String,
    val description: String,
    val stars: Int,
    val images: List<String>,
    val city: String,
    val state: String,
    val country: String,
    val street: String
)