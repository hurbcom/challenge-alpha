package viniciusapp.com.br.hurbtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressModel(
    @field:SerializedName("zipcode")
    var zipcode: String? = null,
    @field:SerializedName("street")
    var street: String? = null,
    @field:SerializedName("street_name")
    var street_name: String? = null,
    @field:SerializedName("streetName")
    var streetName: String? = null,
    @field:SerializedName("address")
    var address: String? = null,
    @field:SerializedName("fullAddress")
    var fullAddress: String? = null,
    @field:SerializedName("full_address")
    var full_address: String? = null,
    @field:SerializedName("neighborhood")
    var neighborhood: String? = null,
    @field:SerializedName("id_atlas_neighborhood")
    var id_atlas_neighborhood: Long? = null,
    @field:SerializedName("id_neighborhood")
    var id_neighborhood: Long? = null,
    @field:SerializedName("city")
    var city: String? = null,
    @field:SerializedName("id_atlas_city")
    var id_atlas_city: Long? = null,
    @field:SerializedName("id_city")
    var id_city: Long? = null,
    @field:SerializedName("state")
    var state: String? = null,
    @field:SerializedName("id_atlas_state")
    var id_atlas_state: Long? = null,
    @field:SerializedName("id_state")
    var id_state: Int? = null,
    @field:SerializedName("country")
    var country: String? = null,
    @field:SerializedName("id_atlas_country")
    var id_atlas_country: Long? = null,
    @field:SerializedName("id_country")
    var id_country: Int? = null,
    @field:SerializedName("countryAlfa2")
    var countryAlfa2: String? = null,
    @field:SerializedName("country_alfa2")
    var country_alfa2: String? = null

): Serializable