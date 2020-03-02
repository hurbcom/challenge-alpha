package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("sku") val sku: String,
    @SerializedName("isHotel") val isHotel: Boolean,
    @SerializedName("isPackage") val isPackage: Boolean,
    @SerializedName("isTicket") val isTicket: Boolean,
    @SerializedName("category") val category: String,
    @SerializedName("smallDescription") val smallDescription: String,
    @SerializedName("amenities") val amenities: List<Amenity>,
    @SerializedName("id") val id: String,
    @SerializedName("price") val price: Price,
    @SerializedName("hu_free_cancellation") val hu_free_cancellation: Boolean,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("description") val description: String,
    @SerializedName("stars") val stars: Int,
    @SerializedName("gallery") val gallery: List<Gallery>,
    @SerializedName("address") val address: Address,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("quantityDescriptors") val quantityDescriptors: QuantityDescriptor,
    @SerializedName("featuredItem") val featuredItem: FeaturedItem
)