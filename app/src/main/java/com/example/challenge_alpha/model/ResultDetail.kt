package com.example.challenge_alpha.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResultDetail(
    @PrimaryKey @field:SerializedName("sku") val sku: String? = null,
    @SerializedName("isHotel") val isHotel: Boolean = false,
    @SerializedName("isPackage") val isPackage: Boolean = false,
    @SerializedName("isService") val isService: Boolean = false,
    @SerializedName("category") val category: String? = null,
    @SerializedName("smallDescription") val smallDescription: String? = null,
    @SerializedName("amenities") val amenities: List<ResultDetailAmenities> = emptyList(),
    @SerializedName("id") val id: String? = null,
    @SerializedName("price") val price: ResultDetailPrice = ResultDetailPrice(),
    @SerializedName("hu_free_cancellation") val freeCancelation: Boolean = false,
    @SerializedName("image") val image: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("stars") val stars: Float? = null,
    @SerializedName("gallery") val gallery: List<ResultDetailGallery> = emptyList(),
    @SerializedName("address") val address: ResultDetailAddress? = null,
    @SerializedName("tags") val tags: List<String>? = null,
    @SerializedName("quantityDescriptors") val quantityDescriptors: ResultDetailQuantityDescriptors? = null,
    @SerializedName("featuredItem") val featuredItem: ResultDetailFeaturedItem? = null
    )

data class ResultDetailAmenities(
    @SerializedName("name") val name: String? = null,
    @SerializedName("category") val category: String? = null
)

data class ResultDetailPrice(
    @SerializedName("amountPerDay") val amount: Float? = null,
    @SerializedName("originalAmountPerDay") val originalAmountPerDay: Float? = null,
    @SerializedName("currency_original") val currency: String? = null,
    @SerializedName("taxes") val taxes: List<ResultDetailTaxes>? = null

)

data class ResultDetailTaxes(
    @SerializedName("type") val type: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("amount_original") val originalAmountPerDay: String? = null,
    @SerializedName("currency_original") val currency: String? = null
)

data class ResultDetailGallery(
    @SerializedName("description") val description: String? = null,
    @SerializedName("url") val url: String? = null
)

data class ResultDetailAddress(
    @SerializedName("zipcode") val zipCode: String? = null,
    @SerializedName("fullAddress") val fullAddress: String? = null,
    @SerializedName("neighborhood") val neighborhood: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("countryAlfa2") val countryCode: String? = null,
    @SerializedName("geoLocation") val geoLocation: ResultDetailGeo? = null
)

data class ResultDetailGeo(
    @SerializedName("lat") val latitude: Double? = null,
    @SerializedName("lon") val longitude: Double? = null
)

data class ResultDetailFeaturedItem(
    @SerializedName("maxChildren") val maxChildren: Int? = null,
    @SerializedName("maxAdults") val maxAdults: Int? = null,
    @SerializedName("maxFreeChildrenAge") val maxFreeChildrenAge: Int? = null
)

data class ResultDetailQuantityDescriptors(
    @SerializedName("amenities") val amenities: List<String>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("description") val description: String? = null
)
