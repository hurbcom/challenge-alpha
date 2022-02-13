package com.isranascimento.datatransferobjects.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class QuantityDescriptorsResponse(
    @JsonProperty("maxChildren") val maxChildren: Int,
    @JsonProperty("maxAdults") val maxAdults: Int,
    @JsonProperty("maxFreeChildrenAge") val maxFreeChildrenAge: Int,
)
