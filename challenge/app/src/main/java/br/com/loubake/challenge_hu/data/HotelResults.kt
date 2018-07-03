package br.com.loubake.challenge_hu.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HotelResults {

    @SerializedName("results")
    @Expose
    lateinit var hotels: List<Hotel>

    class Hotel {
        var name: String? = ""
        var price: Price? = Price()
        var address: Address? = Address()
        var image: String? = ""
        var amenities: List<Amenity>? = arrayListOf()
        var isHotel: Boolean = false
        var isPackage: Boolean = false
        var stars: Int? = 0

        open class Price

        class HotelPrice : Price() {
            @SerializedName("old_price")
            var oldPrice: Double = 0.0
            @SerializedName("current_price")
            var currentPrice: Double = 0.0
        }

        class PackagePrice : Price(){
            var oldPrice: Double = 0.0
            var currentPrice: Double = 0.0
        }

        class Address {
            var country: String = ""
            var state: String = ""
            var city: String = ""
        }

        class Amenity {
            var name: String = ""
            var category: String = ""
        }
    }
}