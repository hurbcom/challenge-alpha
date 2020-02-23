package app.recrutamento.android.challengealpha.model.hotel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Amenity_ {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("category")
    @Expose
    var category: String? = null

}
