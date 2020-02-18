package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HotelModel(
    @field:SerializedName("results")
    var results: List<ResultsModel>? = null

) : Serializable