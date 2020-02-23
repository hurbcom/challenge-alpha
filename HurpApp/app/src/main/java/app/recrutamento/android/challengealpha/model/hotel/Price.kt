package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Price {

    @SerializedName("min")
    @Expose
    var min: Int? = null
    @SerializedName("maxExclusive")
    @Expose
    var maxExclusive: Int? = null
    @SerializedName("filter")
    @Expose
    var filter: String? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null

}
