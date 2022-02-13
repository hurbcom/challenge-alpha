package com.isranascimento.datatransferobjects.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class HotelsResponse(
    @JsonProperty("results") val results: List<HotelResponse>

)
