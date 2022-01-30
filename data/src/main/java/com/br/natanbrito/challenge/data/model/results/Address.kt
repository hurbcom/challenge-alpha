package com.br.natanbrito.challenge.data.model.results

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val city: String,
    val country: String,
    val geoLocation: GeoLocation,
    @SerializedName("id_atlas_city")
    val idAtlasCity: Int?,
    @SerializedName("id_atlas_country")
    val idAtlasCountry: Int?,
    @SerializedName("id_atlas_neighborhood")
    val idAtlasNeighborhood: Int?,
    @SerializedName("id_atlas_state")
    val idAtlasState: Int?,
    @SerializedName("id_city")
    val idCity: Int,
    @SerializedName("id_country")
    val idCountry: Int,
    @SerializedName("id_state")
    val idState: Int,
    val state: String,
    val street: String,
    val zipcode: String
) : Parcelable

@Parcelize
data class GeoLocation(
    val lat: Double,
    val lon: Double
) : Parcelable
