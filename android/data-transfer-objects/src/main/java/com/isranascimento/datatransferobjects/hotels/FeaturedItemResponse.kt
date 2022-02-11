package com.isranascimento.datatransferobjects.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class FeaturedItemResponse(
    @JsonProperty("amenities") val amenities: List<String>?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("image") val image: String?,
    @JsonProperty("description") val description: String?,
)
