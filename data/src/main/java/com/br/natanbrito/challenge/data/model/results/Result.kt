package com.br.natanbrito.challenge.data.model.results

import android.os.Parcelable
import com.br.natanbrito.challenge.data.utils.HTTPS_CONSTANT
import com.br.natanbrito.challenge.data.utils.HTTP_CONSTANT
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val address: Address,
    val amenities: List<AmenityResults>,
    val category: String,
    val description: String,
    val featuredItem: FeaturedItem,
    val gallery: List<Gallery>,
    @SerializedName("hu_free_cancellation")
    val hasFreeCancellation: Boolean,
    val id: String,
    val image: String,
    val isHotel: Boolean,
    val name: String,
    val price: PriceResults,
    val quantityDescriptors: QuantityDescriptors,
    val sku: String,
    val smallDescription: String,
    val stars: Int,
    val tags: List<String>,
    val url: String
) : Parcelable {
    fun convertFromHttpToHttps(): String = if (image.startsWith(HTTP_CONSTANT)) {
        image.replace(
            HTTP_CONSTANT,
            HTTPS_CONSTANT
        )
    } else {
        image
    }
}

@Parcelize
data class Gallery(
    val description: String,
    val url: String
) : Parcelable {
    fun convertFromHttpToHttps(): String = if (url.startsWith(HTTP_CONSTANT)) {
        url.replace(
            HTTP_CONSTANT,
            HTTPS_CONSTANT
        )
    } else {
        url
    }
}

@Parcelize
data class FeaturedItem(
    val amenities: List<String>,
    val description: String,
    val image: String,
    val name: String
) : Parcelable
