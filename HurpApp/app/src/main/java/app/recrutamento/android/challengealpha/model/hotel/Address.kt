package app.recrutamento.android.challengealpha.model.hotel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address {

    @SerializedName("zipcode")
    @Expose
    var zipcode: String? = null
    @SerializedName("street")
    @Expose
    var street: String? = null
    @SerializedName("street_name")
    @Expose
    var streetName: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("full_address")
    @Expose
    var fullAddress: String? = null
    @SerializedName("neighborhood")
    @Expose
    var neighborhood: String? = null
    @SerializedName("id_atlas_neighborhood")
    @Expose
    var idAtlasNeighborhood: Any? = null
    @SerializedName("id_neighborhood")
    @Expose
    var idNeighborhood: Any? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("id_atlas_city")
    @Expose
    var idAtlasCity: Any? = null
    @SerializedName("id_city")
    @Expose
    var idCity: Int? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("id_atlas_state")
    @Expose
    var idAtlasState: Any? = null
    @SerializedName("id_state")
    @Expose
    var idState: Int? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("id_atlas_country")
    @Expose
    var idAtlasCountry: Any? = null
    @SerializedName("id_country")
    @Expose
    var idCountry: Int? = null
    @SerializedName("country_alfa2")
    @Expose
    var countryAlfa2: String? = null
    @SerializedName("geoLocation")
    @Expose
    var geoLocation: GeoLocation? = null

}
