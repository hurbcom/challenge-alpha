package br.com.hurbandroidchallenge.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("categories")
class HomeCategoriesEntity(
    @PrimaryKey
    val url: String
)