package com.isranascimento.datatransferobjects.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class GalleryResponse(
    @JsonProperty("description") val description: String,
    @JsonProperty("url") val url: String,
)
