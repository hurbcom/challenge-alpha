package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultsModel(
    @field:SerializedName("sku")
    var sku: String? = null,
    @field:SerializedName("isHotel")
    var isHotel: Boolean? = true,
    @field:SerializedName("category")
    var category: String? = null,
    @field:SerializedName("smallDescription")
    var smallDescription: String? = null,
    @field:SerializedName("amenities")
    var amenities: List<AmenitiesModel>? = null,
    @field:SerializedName("id")
    var id: String? = null,
    @field:SerializedName("price")
    var price: PriceModel? = null,
    @field:SerializedName("hu_free_cancellation")
    var hu_free_cancellation: Boolean? = null,
    @field:SerializedName("image")
    var image: String? = null,
    @field:SerializedName("name")
    var nameHotel: String? = null,
    @field:SerializedName("url")
    var url: String? = null,
    @field:SerializedName("description")
    var description: String? = null,
    @field:SerializedName("stars")
    var stars: String? = null,
    @field:SerializedName("gallery")
    var gallery: ArrayList<GalleryModel>? = null,
    @field:SerializedName("address")
    var address: AddressModel? = null,
    @field:SerializedName("tags")
    var tags: List<String>? = null,
    @field:SerializedName("quantityDescriptors")
    var quantityDescriptors: QuantityDescriptorsModel? = null,
    @field:SerializedName("featuredItem")
    var featuredItem: FeaturedItemModel? = null
): Serializable