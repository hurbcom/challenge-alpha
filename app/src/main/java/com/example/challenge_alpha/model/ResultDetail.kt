package com.example.challenge_alpha.model

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(indices = [
    Index("sku", unique = true)]
)
data class ResultDetail(
    @PrimaryKey @field:SerializedName("sku") var sku: String = "",
    @field:SerializedName("isHotel") var hotelIs: Boolean = false,
    @field:SerializedName("isPackage") var packageIs: Boolean = false,
    @field:SerializedName("isService") var serviceIs: Boolean = false,
    @field:SerializedName("category") var category: String? = null,
    @field:SerializedName("smallDescription") var smallDescription: String? = null,
    @Ignore @SerializedName("amenities") var amenities: List<ResultDetailAmenities> = emptyList(),
    @field:SerializedName("id") var id: String? = null,
    @Embedded @SerializedName("price") var price: ResultDetailPrice = ResultDetailPrice(),
    @field:SerializedName("hu_free_cancellation") var freeCancelation: Boolean = false,
    @field:SerializedName("image") var image: String? = null,
    @field:SerializedName("name") var name: String? = null,
    @field:SerializedName("url") var url: String? = null,
    @field:SerializedName("description") var description: String? = null,
    @field:SerializedName("stars") var stars: Float? = null,
    @Ignore @SerializedName("gallery") var gallery: List<ResultDetailGallery> = emptyList(),
    @Embedded @SerializedName("address") var address: ResultDetailAddress? = null,
    @SerializedName("tags") var tags: List<String>? = null,
    @Embedded @SerializedName("quantityDescriptors") var quantityDescriptors: ResultDetailQuantityDescriptors? = null,
    @Embedded @SerializedName("featuredItem") var featuredItem: ResultDetailFeaturedItem? = null
)

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ResultDetail::class,
            parentColumns = arrayOf("sku"),
            childColumns = arrayOf("sku"),
            onDelete = ForeignKey.CASCADE
        )
    ), indices = [
        Index("sku", unique = false)]
)
data class ResultDetailAmenities(
    var sku: String? = null,
    @field:SerializedName("name") var name: String? = null,
    @field:SerializedName("category") var category: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var localID: Int = 0
}

data class ResultDetailPrice(
    @field:SerializedName("amountPerDay") var amount: Float? = null,
    @field:SerializedName("originalAmountPerDay") var originalAmountPerDay: Float? = null,
    @field:SerializedName("currency_original") var currency: String? = null,
    @Ignore @SerializedName("taxes") var taxes: List<ResultDetailTaxes>? = null

)

data class ResultDetailTaxes(
    @field:SerializedName("type") var type: String? = null,
    @ColumnInfo(name = "taxes_name") @SerializedName("name") var name: String? = null,
    @field:SerializedName("amount") var amount: String? = null,
    @field:SerializedName("amount_original") var originalAmountPerDay: String? = null,
    @field:SerializedName("currency_original") var currency: String? = null
)

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ResultDetail::class,
            parentColumns = arrayOf("sku"),
            childColumns = arrayOf("sku"),
            onDelete = ForeignKey.CASCADE
        )
    ), indices = [
        Index("sku", unique = false)]
)
data class ResultDetailGallery(
    @field:SerializedName("sku") var sku: String? = null,
    @field:SerializedName("description") var description: String? = null,
    @field:SerializedName("url") var url: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var localID: Int = 0
}

data class ResultDetailAddress(
    @field:SerializedName("zipcode") var zipCode: String? = null,
    @field:SerializedName("fullAddress") var fullAddress: String? = null,
    @field:SerializedName("neighborhood") var neighborhood: String? = null,
    @field:SerializedName("city") var city: String? = null,
    @field:SerializedName("state") var state: String? = null,
    @field:SerializedName("country") var country: String? = null,
    @field:SerializedName("countryAlfa2") var countryCode: String? = null,
    @Embedded @SerializedName("geoLocation") var geoLocation: ResultDetailGeo? = null
)

data class ResultDetailGeo(
    @field:SerializedName("lat") var latitude: Double? = null,
    @field:SerializedName("lon") var longitude: Double? = null
)

data class ResultDetailFeaturedItem(
    @field:SerializedName("maxChildren") var maxChildren: Int? = null,
    @field:SerializedName("maxAdults") var maxAdults: Int? = null,
    @field:SerializedName("maxFreeChildrenAge") var maxFreeChildrenAge: Int? = null
)

data class ResultDetailQuantityDescriptors(
    @ColumnInfo(name = "quantity_amenities")@field:SerializedName("amenities") var amenities: List<String>? = null,
    @ColumnInfo(name = "quantity_name")@field:SerializedName("name") var name: String? = null,
    @ColumnInfo(name = "quantity_image") @field:SerializedName("image") var image: String? = null,
    @ColumnInfo(name = "quantity_description") @field:SerializedName("description") var description: String? = null
)
