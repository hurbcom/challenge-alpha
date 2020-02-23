package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tax {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("amount")
    @Expose
    var amount: Double? = null
    @SerializedName("amount_original")
    @Expose
    var amountOriginal: Double? = null
    @SerializedName("currency")
    @Expose
    var currency: String? = null
    @SerializedName("currency_original")
    @Expose
    var currencyOriginal: String? = null

}
