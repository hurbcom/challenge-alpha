package br.com.hurbandroidchallenge.domain.model

import androidx.compose.ui.layout.ContentScale

data class ItemModel(
    val url: String,
    val image: String,
    val aspectRatio: Float,
    val contentScale: ContentScale = ContentScale.FillHeight,
    val fields: List<Pair<String, String>>
)