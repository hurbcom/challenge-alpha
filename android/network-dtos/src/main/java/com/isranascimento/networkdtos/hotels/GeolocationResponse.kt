package com.isranascimento.networkdtos.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class GeolocationResponse(
    @JsonProperty("lat") val lat: Float?,
    @JsonProperty("lon") val lon: Float?,
)
