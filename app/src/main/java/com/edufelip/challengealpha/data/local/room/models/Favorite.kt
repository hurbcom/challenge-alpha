package com.edufelip.challengealpha.data.local.room.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_table")
@Parcelize
data class Favorite (
    @PrimaryKey(autoGenerate = false)
    var url: String,
    var name: String,
    var imageUrl: String
) : Parcelable