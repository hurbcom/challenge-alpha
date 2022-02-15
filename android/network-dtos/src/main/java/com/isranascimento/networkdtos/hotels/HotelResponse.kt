package com.isranascimento.networkdtos.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class HotelResponse(
    @JsonProperty("sku") val sku: String,
    @JsonProperty("isHotel") val isHotel: Boolean,
    @JsonProperty("category") val category: String,
    @JsonProperty("smallDescription") val smallDescription: String,
    @JsonProperty("amenities") val amenities: List<AmenityResponse>,
    @JsonProperty("id") val id: String,
    @JsonProperty("price") val price: PriceResponse,
    @JsonProperty("hu_free_cancellation") val huFreeCancellation: Boolean,
    @JsonProperty("image") val image: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("stars") val stars: Int,
    @JsonProperty("gallery") val gallery: List<GalleryResponse>,
    @JsonProperty("address") val address: AddressResponse,
    @JsonProperty("tags") val tags: List<String>,
    @JsonProperty("quantityDescriptors") val quantityDescriptors: QuantityDescriptorsResponse,
    @JsonProperty("featuredItem") val featuredItem: FeaturedItemResponse,
)
