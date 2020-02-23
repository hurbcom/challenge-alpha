package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Filters {

    @SerializedName("amenities")
    @Expose
    var amenities: List<Amenity>? = null
    @SerializedName("attributes")
    @Expose
    var attributes: List<Attribute>? = null
    @SerializedName("countries")
    @Expose
    var countries: List<Country>? = null
    @SerializedName("cities")
    @Expose
    var cities: List<City>? = null
    @SerializedName("departureCities")
    @Expose
    var departureCities: List<DepartureCity>? = null
    @SerializedName("prices")
    @Expose
    var prices: List<Price>? = null
    @SerializedName("priceInterval")
    @Expose
    var priceInterval: PriceInterval? = null
    @SerializedName("productType")
    @Expose
    var productType: List<ProductType>? = null
    @SerializedName("regulation")
    @Expose
    var regulation: List<Regulation>? = null
    @SerializedName("rooms")
    @Expose
    var rooms: List<Room>? = null
    @SerializedName("stars")
    @Expose
    var stars: List<Star>? = null
    @SerializedName("states")
    @Expose
    var states: List<State>? = null

}
