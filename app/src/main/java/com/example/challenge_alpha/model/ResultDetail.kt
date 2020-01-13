package com.example.challenge_alpha.model


import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity(
    primaryKeys = ["sku"],
    indices = [
        Index("sku", unique = true)]
)
data class ResultDetail(
    @field:SerializedName("sku") var sku: String = "",
    @field:SerializedName("isHotel") var hotelIs: Boolean = false,
    @field:SerializedName("isPackage") var packageIs: Boolean = false,
    @field:SerializedName("isService") var serviceIs: Boolean = false,
    @field:SerializedName("category") var category: String? = null,
    @field:SerializedName("smallDescription") var smallDescription: String? = null,
    @Ignore @field:SerializedName("amenities") var amenities: List<ResultDetailAmenities> = emptyList(),
    @field:SerializedName("id") var id: String? = null,
    @Embedded @field:SerializedName("price") var price: ResultDetailPrice = ResultDetailPrice(),
    @field:SerializedName("hu_free_cancellation") var freeCancelation: Boolean = false,
    @field:SerializedName("image") var image: String? = null,
    @field:SerializedName("name") var name: String? = null,
    @field:SerializedName("url") var url: String? = null,
    @field:SerializedName("description") var description: String? = null,
    @field:SerializedName("stars") var stars: Float? = null,
    @Ignore @field:SerializedName("gallery") var gallery: List<ResultDetailGallery> = emptyList(),
    @Embedded @field:SerializedName("address") var address: ResultDetailAddress? = null,
    @field:SerializedName("tags") var tags: List<String>? = null,
    @Embedded @field:SerializedName("quantityDescriptors") var quantityDescriptors: ResultDetailQuantityDescriptors? = null,
    @Embedded @field:SerializedName("featuredItem") var featuredItem: ResultDetailFeaturedItem? = null,
    @Ignore var recyclerTitle: Boolean = false,
    var timestamp: Date = Date(0)
)


@Entity(
    foreignKeys = [ForeignKey(
        entity = ResultDetail::class,
        parentColumns = arrayOf("sku"),
        childColumns = arrayOf("sku"),
        onDelete = ForeignKey.CASCADE
    )], indices = [
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
    @Ignore @field:SerializedName("taxes") var taxes: List<ResultDetailTaxes>? = null

)

@Entity(
    foreignKeys = [ForeignKey(
        entity = ResultDetail::class,
        parentColumns = arrayOf("sku"),
        childColumns = arrayOf("sku"),
        onDelete = ForeignKey.CASCADE
    )], indices = [
        Index("sku", unique = false)]
)
data class ResultDetailTaxes(
    var sku: String? = null,
    @field:SerializedName("type") var type: String? = null,
    @ColumnInfo(name = "taxes_name") @field:SerializedName("name") var name: String? = null,
    @field:SerializedName("amount") var amount: String? = null,
    @field:SerializedName("amount_original") var originalAmountPerDay: String? = null,
    @field:SerializedName("currency_original") var currency: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var localID: Int = 0
}

@Entity(
    foreignKeys = [ForeignKey(
        entity = ResultDetail::class,
        parentColumns = arrayOf("sku"),
        childColumns = arrayOf("sku"),
        onDelete = ForeignKey.CASCADE
    )], indices = [
        Index("sku", unique = false)]
)
data class ResultDetailGallery(
    var sku: String? = null,
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
    @Embedded @field:SerializedName("geoLocation") var geoLocation: ResultDetailGeo? = null
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
    @ColumnInfo(name = "quantity_amenities") @field:SerializedName("amenities") var amenities: List<String>? = null,
    @ColumnInfo(name = "quantity_name") @field:SerializedName("name") var name: String? = null,
    @ColumnInfo(name = "quantity_image") @field:SerializedName("image") var image: String? = null,
    @ColumnInfo(name = "quantity_description") @field:SerializedName("description") var description: String? = null
)
