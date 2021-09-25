package com.filipeoliveira.hurbchallenge.data.remote

data class FeaturedItemResponse(
    val amenities: List<String>?,
    val description: String?,
    val image: String?,
    val name: String?
)