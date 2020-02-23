package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Price_ {

    @SerializedName("amount")
    @Expose
    var amount: Double? = null
    @SerializedName("old_price")
    @Expose
    var oldPrice: Double? = null
    @SerializedName("currency")
    @Expose
    var currency: String? = null
    @SerializedName("currency_original")
    @Expose
    var currencyOriginal: String? = null
    @SerializedName("gain")
    @Expose
    var gain: Int? = null
    @SerializedName("fee_extra_original")
    @Expose
    var feeExtraOriginal: Int? = null
    @SerializedName("gain_original")
    @Expose
    var gainOriginal: Int? = null
    @SerializedName("tariff_policies")
    @Expose
    var tariffPolicies: List<Any>? = null
    @SerializedName("current_price")
    @Expose
    var currentPrice: Double? = null
    @SerializedName("total_price")
    @Expose
    var totalPrice: Double? = null
    @SerializedName("fee_extra")
    @Expose
    var feeExtra: Int? = null
    @SerializedName("sku")
    @Expose
    var sku: String? = null
    @SerializedName("taxes")
    @Expose
    var taxes: List<Tax>? = null
    @SerializedName("originalAmountPerDay")
    @Expose
    var originalAmountPerDay: Double? = null
    @SerializedName("amountPerDay")
    @Expose
    var amountPerDay: Double? = null

}
