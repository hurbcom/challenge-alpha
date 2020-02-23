package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FeaturedItem {

    @SerializedName("amenities")
    @Expose
    var amenities: List<String>? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null

}
