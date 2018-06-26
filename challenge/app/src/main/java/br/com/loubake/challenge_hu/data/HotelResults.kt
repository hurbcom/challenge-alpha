package br.com.loubake.challenge_hu.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HotelResults {

    @SerializedName("results")
    @Expose
    lateinit var hotels: List<Hotel>

    class Hotel {
        var name: String? = null
        var price: Price? = null
        var address: Address? = null
        var image: String? = null
        var amenities: List<Amenity>? = null

        class Price {
            var oldPrice: Double = 0.0
            var currentPrice: Double = 0.0
        }

        class Address {
            lateinit var country: String
            lateinit var state: String
            lateinit var city: String
        }

        class Amenity {
            lateinit var name: String
            lateinit var category: String
        }
    }
}