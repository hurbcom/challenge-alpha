package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Address(

    @SerializedName("zipcode") val zipcode: String,
    @SerializedName("street") val street: String,
    @SerializedName("street_name") val street_name: String,
    @SerializedName("streetName") val streetName: String,
    @SerializedName("address") val address: String,
    @SerializedName("fullAddress") val fullAddress: String,
    @SerializedName("full_address") val full_address: String,
    @SerializedName("neighborhood") val neighborhood: String,
    @SerializedName("id_atlas_neighborhood") val id_atlas_neighborhood: String,
    @SerializedName("id_neighborhood") val id_neighborhood: String,
    @SerializedName("city") val city: String,
    @SerializedName("id_atlas_city") val id_atlas_city: String,
    @SerializedName("id_city") val id_city: Int,
    @SerializedName("state") val state: String,
    @SerializedName("id_atlas_state") val id_atlas_state: String,
    @SerializedName("id_state") val id_state: Int,
    @SerializedName("country") val country: String,
    @SerializedName("id_atlas_country") val id_atlas_country: String,
    @SerializedName("id_country") val id_country: Int,
    @SerializedName("countryAlfa2") val countryAlfa2: String,
    @SerializedName("country_alfa2") val country_alfa2: String,
    @SerializedName("geoLocation") val geoLocation: GeoLocation
)