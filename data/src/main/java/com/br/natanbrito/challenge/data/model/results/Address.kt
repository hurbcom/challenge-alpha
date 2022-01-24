package com.br.natanbrito.challenge.data.model.results

data class Address(
    val city: String,
    val country: String,
    val geoLocation: GeoLocation,
    val id_atlas_city: Int?,
    val id_atlas_country: Int?,
    val id_atlas_neighborhood: Int?,
    val id_atlas_state: Int?,
    val id_city: Int,
    val id_country: Int,
    val id_state: Int,
    val state: String,
    val street: String,
    val zipcode: String
)

data class GeoLocation(
    val lat: Double,
    val lon: Double
)