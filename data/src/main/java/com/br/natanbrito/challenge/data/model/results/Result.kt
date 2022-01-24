package com.br.natanbrito.challenge.data.model.results

data class Result(
    val address: Address,
    val amenities: List<AmenityResults>,
    val category: String,
    val description: String,
    val featuredItem: FeaturedItem,
    val gallery: List<Gallery>,
    val hu_free_cancellation: Boolean,
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
) /*{
    fun convertFromHttpToHttps(): String = if (image.startsWith(HTTP_CONSTANT)) {
        image.replace(
            HTTP_CONSTANT,
            HTTPS_CONSTANT
        )
    } else {
        image
    }
}*/


data class Gallery(
    val description: String,
    val url: String
)

data class FeaturedItem(
    val amenities: List<String>,
    val description: String,
    val image: String,
    val name: String
)

