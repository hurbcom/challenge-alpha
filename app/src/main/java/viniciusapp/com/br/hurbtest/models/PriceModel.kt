package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PriceModel(
    @field:SerializedName("amount")
    var amount: Double? = null,
    @field:SerializedName("old_price")
    var old_price: Double? = null,
    @field:SerializedName("currency")
    var currency: String? = null,
    @field:SerializedName("currency_original")
    var currency_original: String? = null,
    @field:SerializedName("gain")
    var gain: Int? = null,
    @field:SerializedName("fee_extra_original")
    var fee_extra_original: Int? = null,
    @field:SerializedName("gain_original")
    var gain_original: Int? = null,
    @field:SerializedName("current_price")
    var current_price: Double? = null,
    @field:SerializedName("total_price")
    var total_price: Double? = null,
    @field:SerializedName("fee_extra")
    var fee_extra: Int? = null,
    @field:SerializedName("sku")
    var sku: String? = null,
    @field:SerializedName("taxes")
    var taxes: List<TaxesModel>? = null,
    @field:SerializedName("originalAmountPerDay")
    var originalAmountPerDay: Double? = null,
    @field:SerializedName("amountPerDay")
    var amountPerDay: Double? = null

) : Serializable