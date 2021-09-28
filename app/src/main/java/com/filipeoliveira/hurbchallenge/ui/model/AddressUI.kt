package com.filipeoliveira.hurbchallenge.ui.model

import java.io.Serializable

data class AddressUI(
    val city: String,
    val country: String,
    val street: String,
    val state: String
): Serializable {
    fun getStreetAndCityAsString(): String {
        return "$street, $city"
    }

    fun getCityAndStateAsString(): String {
        return "$city, $state"
    }
}