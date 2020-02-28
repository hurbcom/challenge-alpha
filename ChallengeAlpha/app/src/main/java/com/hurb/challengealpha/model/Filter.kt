package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Filter(

    @SerializedName("amenities") val amenities: List<Amenity>,
    @SerializedName("attributes") val attributes: List<Attribute>,
    @SerializedName("countries") val countries: List<Country>,
    @SerializedName("cities") val cities: List<City>,
    @SerializedName("departureCities") val departureCities: List<DepartureCity>,
    @SerializedName("prices") val prices: List<PriceGroup>,
    @SerializedName("priceInterval") val priceInterval: PriceInterval,
    @SerializedName("productType") val productType: List<ProductType>,
    @SerializedName("regulation") val regulation: List<Regulation>,
    @SerializedName("rooms") val rooms: List<Room>,
    @SerializedName("stars") val stars: List<Star>,
    @SerializedName("states") val states: List<State>
)