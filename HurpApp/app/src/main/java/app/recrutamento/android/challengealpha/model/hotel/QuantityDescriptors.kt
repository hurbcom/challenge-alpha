package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuantityDescriptors {

    @SerializedName("maxChildren")
    @Expose
    var maxChildren: Int? = null
    @SerializedName("maxAdults")
    @Expose
    var maxAdults: Int? = null
    @SerializedName("maxFreeChildrenAge")
    @Expose
    var maxFreeChildrenAge: Int? = null

}
