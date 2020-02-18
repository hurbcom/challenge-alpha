package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaxesModel(
    @field:SerializedName("type")
    var type: String? = null,
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("amount")
    var amount: Double? = null,
    @field:SerializedName("amount_original")
    var amount_original: Double? = null,
    @field:SerializedName("currency")
    var currency: String? = null,
    @field:SerializedName("currency_original")
    var currency_original: String? = null
): Serializable