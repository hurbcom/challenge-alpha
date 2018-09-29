package br.com.hu.allyson.desafiohu.domain


data class Address(
    val city: String,
    val country: String,
    val state: String,
    val street: String,
    val zipcode: String,
    val geoLocation: GeoLocation
)
