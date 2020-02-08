package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Hotel(
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
    @SerializedName("hu_free_cancellation")
    val huFreeCancellation: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
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
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("url")
    val url: String
)