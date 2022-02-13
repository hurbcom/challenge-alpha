package com.isranascimento.datatransferobjects.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class AmenityResponse(
    @JsonProperty("name") val name: String,
    @JsonProperty("category") val category: String
)
