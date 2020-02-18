package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QuantityDescriptorsModel(
    var maxChildren: Int? = null,
    @field:SerializedName("maxAdults")
    var maxAdults: Int? = null,
    @field:SerializedName("maxFreeChildrenAge")
    var maxFreeChildrenAge: Int? = null
) : Serializable