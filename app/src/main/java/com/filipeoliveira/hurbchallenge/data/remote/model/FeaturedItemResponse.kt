package com.filipeoliveira.hurbchallenge.data.remote.model

data class FeaturedItemResponse(
    val amenities: List<String>?,
    val description: String?,
    val image: String?,
    val name: String?
)