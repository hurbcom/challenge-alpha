package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FeaturedItemModel(
    @field:SerializedName("amenities")
    var amenities: List<String>? = null,
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("image")
    var image: String? = null,
    @field:SerializedName("description")
    var description: String? = null
) : Serializable