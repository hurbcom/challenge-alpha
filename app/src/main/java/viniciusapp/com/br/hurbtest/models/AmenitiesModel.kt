package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AmenitiesModel(
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("category")
    var category: String? = null
): Serializable