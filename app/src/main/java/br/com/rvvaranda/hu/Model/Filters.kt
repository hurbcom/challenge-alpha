package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("amenities")
    val amenities: List<Amenity_Filter>,
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
    @SerializedName("rooms")
    val rooms: List<Room>,
    @SerializedName("stars")
    val stars: List<Star>,
    @SerializedName("states")
    val states: List<State>
)