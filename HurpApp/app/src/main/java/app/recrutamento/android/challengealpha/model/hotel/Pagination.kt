package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pagination {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("firstPage")
    @Expose
    var firstPage: String? = null
    @SerializedName("nextPage")
    @Expose
    var nextPage: String? = null
    @SerializedName("previousPage")
    @Expose
    var previousPage: Any? = null
    @SerializedName("lastPage")
    @Expose
    var lastPage: String? = null

}
