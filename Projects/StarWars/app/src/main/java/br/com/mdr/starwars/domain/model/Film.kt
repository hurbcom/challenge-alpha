package br.com.mdr.starwars.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import br.com.mdr.starwars.common.Constants
import br.com.mdr.starwars.common.Constants.FILMS_URL
import br.com.mdr.starwars.common.Constants.FILM_DATABASE_TABLE
import br.com.mdr.starwars.common.Constants.IMAGE_BASE_URL
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = FILM_DATABASE_TABLE)
data class Film(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("episode_id")
    val id: Int,
    val title: String,
    val url: String,
    val director: String,
    val producer: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String
) {
    @get:Ignore
    val filmUrl
        get() = getImageUrl()

    private fun getImageUrl(): String {
        val id = url[url.length - 2]
        return "$IMAGE_BASE_URL$FILMS_URL$id${Constants.IMAGE_EXTENSION}"
    }
}
