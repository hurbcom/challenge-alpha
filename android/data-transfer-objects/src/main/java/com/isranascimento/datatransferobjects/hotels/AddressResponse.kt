package com.isranascimento.datatransferobjects.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class AddressResponse(
    @JsonProperty("city") val city: String?,
    @JsonProperty("country") val country: String?,
    @JsonProperty("id_atlas_city") val idAtlasCity: Int?,
    @JsonProperty("id_atlas_country") val idAtlasCountry: Int?,
    @JsonProperty("id_atlas_neighborhood") val idAtlasNeighborhood: Int?,
    @JsonProperty("id_atlas_state") val idAtlasState: Int?,
    @JsonProperty("id_city") val idCity: Int?,
    @JsonProperty("id_country") val idCountry: Int?,
    @JsonProperty("id_state") val idState: Int?,
    @JsonProperty("state") val state: String?,
    @JsonProperty("street") val street: String?,
    @JsonProperty("zipcode") val zipcode: String?,
    @JsonProperty("geoLocation") val geolocation: GeolocationResponse?,
)
