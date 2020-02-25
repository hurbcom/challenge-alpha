package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("sku")
    @Expose
    var sku: String? = null,
    @SerializedName("isHotel")
    @Expose
    var isHotel: Boolean? = null,
    @SerializedName("category")
    @Expose
    var category: String? = null,
    @SerializedName("smallDescription")
    @Expose
    var smallDescription: String? = null,
    @SerializedName("amenities")
    @Expose
    var amenities: List<Amenity_>? = null,
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("price")
    @Expose
    var price: Price_? = null,
    @SerializedName("hu_free_cancellation")
    @Expose
    var huFreeCancellation: Boolean? = null,
    @SerializedName("image")
    @Expose
    var image: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("stars")
    @Expose
    var stars: Int? = null,
    @SerializedName("gallery")
    @Expose
    var gallery: List<Gallery>? = null,
    @SerializedName("address")
    @Expose
    var address: Address? = null,
    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null,
    @SerializedName("quantityDescriptors")
    @Expose
    var quantityDescriptors: QuantityDescriptors? = null,
    @SerializedName("featuredItem")
    @Expose
    var featuredItem: FeaturedItem? = null
)


