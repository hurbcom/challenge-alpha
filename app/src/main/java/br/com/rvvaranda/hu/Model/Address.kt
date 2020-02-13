package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("countryAlfa2")
    val countryAlfa2: String,
    @SerializedName("country_alfa2")
    val country_Alfa2: String,
    @SerializedName("fullAddress")
    val fullAddress: String,
    @SerializedName("full_address")
    val full_Address: String,
    @SerializedName("geoLocation")
    val geoLocation: GeoLocation,
    @SerializedName("id_atlas_city")
    val idAtlasCity: Any,
    @SerializedName("id_atlas_country")
    val idAtlasCountry: Any,
    @SerializedName("id_atlas_state")
    val idAtlasState: Any,
    @SerializedName("id_city")
    val idCity: Int,
    @SerializedName("id_country")
    val idCountry: Int,
    @SerializedName("id_state")
    val idState: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("street_name")
    val street_Name: String,
    @SerializedName("streetName")
    val streetName: String,
    @SerializedName("zipcode")
    val zipcode: String


)