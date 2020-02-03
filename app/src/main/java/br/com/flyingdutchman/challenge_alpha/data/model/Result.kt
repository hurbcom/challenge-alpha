package br.com.flyingdutchman.challenge_alpha.data.model

import br.com.flyingdutchman.challenge_alpha.data.model.*
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("address")
    val address: Address,
    @SerializedName("amenities")
    val amenities: List<Amenity>,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("featuredItem")
    val featuredItem: FeaturedItem,
    @SerializedName("gallery")
    val gallery: List<Gallery>,
    @SerializedName("id")
    val id: String,
    @SerializedName("isPackage")
    val isPackage: Boolean,
    @SerializedName("isHotel")
    val isHotel: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: PriceX,
    @SerializedName("quantityDescriptors")
    val quantityDescriptors: QuantityDescriptors,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("smallDescription")
    val smallDescription: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("hu_free_cancellation")
    val freeCancelation: Boolean,
    @SerializedName("stars")
    val stars: Int
)