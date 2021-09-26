package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class HotelResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("smallDescription")
    val smallDescription: String?,
    @SerializedName("amenities")
    val amenities: List<AmenitiesResponse>?,
    @SerializedName("price")
    val price: PriceResponse?,
    @SerializedName("hu_free_cancellation")
    val huFreeCancellation: Boolean?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("description")
    val description: String ?,
    @SerializedName("stars")
    val starts: Int,
    @SerializedName("gallery")
    val images: List<ImageResponse>,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("quantityDescriptors")
    val quantityDescriptors: QuantityDescriptorsResponse?

)