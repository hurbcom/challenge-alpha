package com.example.challenge_alpha.model


import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * A class [ResultDetail] define os dados dos hotéis/pacotes recebidos do servidor, assim como
 * define os parametros para criação das databases
 * [ResultDetailDataBase], [LastSeenDatabase], [FavoritesDatabase].
 *
 * Para alcançar o objetivo de armazenar todos os dados foi necessário criar uma classe de apoio
 * [ResultDetailRelation].
 *
 * A classe [HurbResponse] agrupa todos os dados recebidos no JSON do servidor através do retrofit
 * [HurbCall]
 *
 */

@Entity(
    primaryKeys = ["sku"],
    indices = [
        Index("sku", unique = true)]
)
data class ResultDetail(
    @field:SerializedName("sku") val sku: String = "",
    @field:SerializedName("isHotel") val hotelIs: Boolean = false,
    @field:SerializedName("isPackage") val packageIs: Boolean = false,
    @field:SerializedName("isService") val serviceIs: Boolean = false,
    @field:SerializedName("category") val category: String? = null,
    @field:SerializedName("smallDescription") val smallDescription: String? = null,
    @field:SerializedName("id") val id: String? = null,
    @Embedded @field:SerializedName("price") val price: ResultDetailPrice? = null,
    @field:SerializedName("hu_free_cancellation") val freeCancelation: Boolean = false,
    @field:SerializedName("image") val image: String? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("url") val url: String? = null,
    @field:SerializedName("description") val description: String? = null,
    @field:SerializedName("stars") val stars: Float? = null,
    @Embedded @field:SerializedName("address") val address: ResultDetailAddress? = null,
    @field:SerializedName("tags") val tags: List<String>? = null,
    @Embedded @field:SerializedName("quantityDescriptors") val quantityDescriptors: ResultDetailQuantityDescriptors? = null,
    @Embedded @field:SerializedName("featuredItem") val featuredItem: ResultDetailFeaturedItem? = null,
    var timestamp: Date = Date(0)
) {
    @Ignore
    var recyclerTitle: Boolean = false

    @Ignore
    @field:SerializedName("amenities")
    val amenities: List<ResultDetailAmenities>? = null

    @Ignore
    @field:SerializedName("gallery")
    val gallery: List<ResultDetailGallery>? = null
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
    @field:SerializedName("amountPerDay") val amount: Float? = null,
    @field:SerializedName("originalAmountPerDay") val originalAmountPerDay: Float? = null,
    @field:SerializedName("currency_original") val currency: String? = null
) {
    @Ignore
    @field:SerializedName("taxes")
    val taxes: List<ResultDetailTaxes>? = null
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
data class ResultDetailTaxes(
    val sku: String? = null,
    @field:SerializedName("type") val type: String? = null,
    @ColumnInfo(name = "taxes_name") @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("amount") val amount: String? = null,
    @field:SerializedName("amount_original") val originalAmountPerDay: String? = null,
    @field:SerializedName("currency_original") val currency: String? = null
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
    @field:SerializedName("description") val description: String? = null,
    @field:SerializedName("url") val url: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var localID: Int = 0
}

data class ResultDetailAddress(
    @field:SerializedName("zipcode") val zipCode: String? = null,
    @field:SerializedName("fullAddress") val fullAddress: String? = null,
    @field:SerializedName("neighborhood") val neighborhood: String? = null,
    @field:SerializedName("city") val city: String? = null,
    @field:SerializedName("state") val state: String? = null,
    @field:SerializedName("country") val country: String? = null,
    @field:SerializedName("countryAlfa2") val countryCode: String? = null,
    @Embedded @field:SerializedName("geoLocation") val geoLocation: ResultDetailGeo? = null
)

data class ResultDetailGeo(
    @field:SerializedName("lat") val latitude: Double? = null,
    @field:SerializedName("lon") val longitude: Double? = null
)

data class ResultDetailFeaturedItem(
    @field:SerializedName("maxChildren") val maxChildren: Int? = null,
    @field:SerializedName("maxAdults") val maxAdults: Int? = null,
    @field:SerializedName("maxFreeChildrenAge") val maxFreeChildrenAge: Int? = null
)

data class ResultDetailQuantityDescriptors(
    @ColumnInfo(name = "quantity_amenities") @field:SerializedName("amenities") val amenities: List<String>? = null,
    @ColumnInfo(name = "quantity_name") @field:SerializedName("name") val name: String? = null,
    @ColumnInfo(name = "quantity_image") @field:SerializedName("image") val image: String? = null,
    @ColumnInfo(name = "quantity_description") @field:SerializedName("description") val description: String? = null
)
