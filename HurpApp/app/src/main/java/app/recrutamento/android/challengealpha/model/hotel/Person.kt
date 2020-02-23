package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Person {

    @SerializedName("term")
    @Expose
    var term: String? = null
    @SerializedName("filter")
    @Expose
    var filter: String? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null

}
