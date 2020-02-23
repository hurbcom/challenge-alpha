package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("query")
    @Expose
    var query: String? = null
    @SerializedName("warning")
    @Expose
    var warning: String? = null
    @SerializedName("countWithAvailabilityInPage")
    @Expose
    var countWithAvailabilityInPage: Int? = null
    @SerializedName("countHotel")
    @Expose
    var countHotel: Int? = null
    @SerializedName("countPackage")
    @Expose
    var countPackage: Int? = null
    @SerializedName("countTicket")
    @Expose
    var countTicket: Int? = null
    @SerializedName("countBustrip")
    @Expose
    var countBustrip: Int? = null
    @SerializedName("countDisney")
    @Expose
    var countDisney: Int? = null

}
