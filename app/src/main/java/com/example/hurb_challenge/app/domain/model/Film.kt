package com.example.hurb_challenge.app.domain.model

import android.os.Parcelable
import com.example.hurb_challenge.BuildConfig
import com.example.hurb_challenge.app.core.encodeUrl
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Film(
    val id: String,
    val title: String,
    val episode: Int,
    val openingCrawl: String,
    val director: String,
    val releaseDate: String,
    val charactersUrl: List<String>
): ImageUrlProvider, Parcelable {

    override fun getImageUrl() = "${BuildConfig.BASE_IMAGE_URL}/films/$id.jpg".encodeUrl()
}
