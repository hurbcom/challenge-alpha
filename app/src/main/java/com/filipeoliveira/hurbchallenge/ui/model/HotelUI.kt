package com.filipeoliveira.hurbchallenge.ui.model

data class HotelUI(
    val id: String,
    val smallDescription: String,
    val amenities: String,
    val priceCurrency: PriceUI,
    val huFreeCancellation: Boolean,
    val image: String,
    val name: String,
    val url: String,
    val description: String,
    val stars: Int,
    val images: List<ImageUI>,
    val tags: List<String>,
    val quantityDescriptors: QuantityDescriptorsUI
)
