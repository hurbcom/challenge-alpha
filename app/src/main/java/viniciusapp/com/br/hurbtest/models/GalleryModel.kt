package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GalleryModel(
    @field:SerializedName("description")
    var description: String? = null,
    @field:SerializedName("url")
    var url: String? = null

): Serializable