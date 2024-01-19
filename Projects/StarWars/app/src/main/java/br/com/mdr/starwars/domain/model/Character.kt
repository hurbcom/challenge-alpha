package br.com.mdr.starwars.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import br.com.mdr.starwars.common.Constants.CHARACTERS_URL
import br.com.mdr.starwars.common.Constants.CHARACTER_TABLE
import br.com.mdr.starwars.common.Constants.IMAGE_BASE_URL
import br.com.mdr.starwars.common.Constants.IMAGE_EXTENSION
import com.google.gson.annotations.SerializedName

@Entity(tableName = CHARACTER_TABLE)
data class Character(
    @PrimaryKey
    val name: String,
    @SerializedName("birth_year")
    val birthYear: String,
    val height: String,
    val mass: String,
    val gender: String,
    val url: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    var isFavorite: Boolean = false
) {
    @get:Ignore
    val characterUrl
        get() = getImageUrl()

    private fun getImageUrl(): String {
        val stringSplit = url.split("/")
        val id = stringSplit[stringSplit.size - 2]
        return "${IMAGE_BASE_URL}${CHARACTERS_URL}$id${IMAGE_EXTENSION}"
    }
}
