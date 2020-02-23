package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PriceInterval {

    @SerializedName("min")
    @Expose
    var min: Int? = null
    @SerializedName("max")
    @Expose
    var max: Int? = null
    @SerializedName("filterPattern")
    @Expose
    var filterPattern: String? = null

}
