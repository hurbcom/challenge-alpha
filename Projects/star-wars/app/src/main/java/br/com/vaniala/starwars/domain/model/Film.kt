package br.com.vaniala.starwars.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import br.com.vaniala.starwars.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */

private const val OPENING_CRAWL_INDEX_START = 0
private const val OPENING_CRAWL_INDEX_END = 4
private const val OPENING_CRAWL_REPLACE_STRING = "\r\n"
private const val MIN_EPISODE = 1
private const val MAX_EPISODE = 6
private const val FILMS_URL = "films/"
private const val JPG = ".jpg"

@Entity(
    tableName = "film",
)
@Parcelize
@JsonClass(generateAdapter = true)
data class Film(
    @PrimaryKey
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "episode_id") val episode_id: Int? = null,
    @field:Json(name = "opening_crawl") var opening_crawl: String? = null,
    @field:Json(name = "director") val director: String? = null,
    @field:Json(name = "producer") val producer: String? = null,
    @field:Json(name = "release_date") var release_date: String? = null,
//    @field:Json(name = "characters") val characters: List<String>?,
    @field:Json(name = "url")
    @ColumnInfo(name = "url_film")
    val url: String? = null,
    @field:Json(ignore = true)
    @ColumnInfo(name = "isFavorite_film")
    var isFavorite: Boolean = false,
    @field:Json(ignore = true)
    @ColumnInfo(name = "timestamp_film")
    var timestamp: Long = 0L,
    @field:Json(ignore = true)
    @ColumnInfo(name = "is_update_film")
    var isUpdate: Boolean = false,
) : Parcelable {
    @IgnoredOnParcel
    var image: String? = getUrlImage(url)

    @Ignore
    @IgnoredOnParcel
    var titleFormatted: String? = getEpisodeTitle(episode_id, title)

    @Ignore
    @IgnoredOnParcel
    var openingCrawl: String? = modifyOpeningCrawl(opening_crawl)

    @Ignore
    @IgnoredOnParcel
    var releaseDate: String? = modifyReleaseDate(release_date)

    private fun getUrlImage(url: String?): String? {
        if (url == null || url.length < 2) return null
        val id = url[url.length - 2]
        return "${BuildConfig.BASE_URL_IMAGES}$FILMS_URL$id$JPG"
    }

    private fun getEpisodeTitle(episodeId: Int?, title: String): String? {
        if (episodeId == null || episodeId < MIN_EPISODE || episodeId > MAX_EPISODE) {
            return null
        }
        val episode = convertToRoman(episodeId)
        return "Episode $episode: $title"
    }

    private fun convertToRoman(episode: Int): String {
        val romanos = arrayOf("I", "II", "III", "IV", "V", "VI")
        return romanos[episode - 1]
    }

    private fun modifyOpeningCrawl(openingCrawl: String?): String? {
        return openingCrawl?.replace(OPENING_CRAWL_REPLACE_STRING, "")
    }

    private fun modifyReleaseDate(releaseDate: String?): String? {
        return releaseDate?.substring(OPENING_CRAWL_INDEX_START, OPENING_CRAWL_INDEX_END)
    }
}
