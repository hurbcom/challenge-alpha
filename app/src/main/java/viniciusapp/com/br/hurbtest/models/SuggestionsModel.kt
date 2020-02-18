package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SuggestionsModel: Serializable {
    @field:SerializedName("text")
    var text: String? = null
    @field:SerializedName("suggestionType")
    var suggestionType: String? = null
    @field:SerializedName("country")
    var country: String? = null
    @field:SerializedName("state")
    var state: String? = null
    @field:SerializedName("city")
    var city: String? = null
    @field:SerializedName("filter")
    var filter: String? = null
    @field:SerializedName("url")
    var url: String? = null

}