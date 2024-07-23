package com.example.hurb_challenge.app.domain.model

data class CardItem<out T>(
    val title: String,
    val subtitle: String,
    val item: T
)
