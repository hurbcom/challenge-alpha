package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("amenities")
    val amenities: ArrayList<Amenity_Filter>,
    @SerializedName("attributes")
    val attributes: ArrayList<Attribute>,
    @SerializedName("cities")
    val cities: ArrayList<City>,
    @SerializedName("countries")
    val countries: ArrayList<Country>,
    @SerializedName("departureCities")
    val departureCities: ArrayList<DepartureCity>,
    @SerializedName("duration")
    val duration: ArrayList<Duration>,
    @SerializedName("food")
    val food: ArrayList<Food>,
    @SerializedName("people")
    val people: ArrayList<People>,
    @SerializedName("priceInterval")
    val priceInterval: PriceInterval,
    @SerializedName("prices")
    val prices: ArrayList<Price>,
    @SerializedName("productType")
    val productType: ArrayList<ProductType>,
    @SerializedName("regulation")
    val regulation: ArrayList<Regulation>,
    @SerializedName("rooms")
    val rooms: ArrayList<Room>,
    @SerializedName("stars")
    val stars: ArrayList<Star>,
    @SerializedName("states")
    val states: ArrayList<State>
)