package com.example.hurb_challenge.app.domain.model

import android.os.Parcelable
import com.example.hurb_challenge.BuildConfig
import com.example.hurb_challenge.app.core.encodeUrl
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Character(
    val id: String,
    val name: String,
    val height: String,
    val gender: String,
    val birthYear: String,
    val vehiclesUrl: List<String>,
    val starshipsUrl: List<String>
): ImageUrlProvider, Parcelable {

    override fun getImageUrl() = "${BuildConfig.BASE_IMAGE_URL}/characters/$id.jpg".encodeUrl()
}
