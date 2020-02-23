package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HotelResponse {

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("filters")
    @Expose
    var filters: Filters? = null
    @SerializedName("results")
    @Expose
    var results: MutableList<Hotel>? = null
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null
}