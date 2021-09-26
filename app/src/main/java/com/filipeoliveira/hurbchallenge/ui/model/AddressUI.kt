package com.filipeoliveira.hurbchallenge.ui.model

data class AddressUI(
    val city: String,
    val country: String,
    val street: String,
    val state: String
) {
    fun getStreetAndCityAsString(): String {
        return "$street, $city"
    }

    fun getCityAndStateAsString(): String {
        return "$city, $state"
    }
}