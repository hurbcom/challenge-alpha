package com.example.hurb_challenge.app.domain.model

import com.example.hurb_challenge.BuildConfig

data class Starship(
    val id: String,
    val name: String,
    val model: String
) : ImageUrlProvider {
    override fun getImageUrl() = BuildConfig.BASE_IMAGE_URL+"/starships/"+id+".jpg"
}

