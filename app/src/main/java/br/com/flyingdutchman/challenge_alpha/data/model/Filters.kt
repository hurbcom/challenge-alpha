package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("attributes")
    val attributes: List<Attribute>,
    @SerializedName("cities")
    val cities: List<City>,
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("departureCities")
    val departureCities: List<DepartureCity>,
    @SerializedName("duration")
    val duration: List<Duration>,
    @SerializedName("food")
    val food: List<Food>,
    @SerializedName("people")
    val people: List<People>,
    @SerializedName("priceInterval")
    val priceInterval: PriceInterval,
    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("productType")
    val productType: List<ProductType>,
    @SerializedName("regulation")
    val regulation: List<Regulation>,
    @SerializedName("states")
    val states: List<State>
)