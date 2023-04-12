package br.com.vaniala.starwars.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class Films(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "opening_crawl") val openingCrawl: String?,
    @field:Json(name = "episode_id") val episodeId: Int?,
    @field:Json(ignore = true) val image: Int?,
) : Parcelable