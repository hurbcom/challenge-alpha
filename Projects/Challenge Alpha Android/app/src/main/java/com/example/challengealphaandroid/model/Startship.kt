package com.example.challengealphaandroid.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.challengealphaandroid.common.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Starship")
data class Starship(
    @PrimaryKey
    @NonNull
    override val name: String,
    val model: String?,
    val starshipClass: String?,
    var id: Int? = null,
    val url: String? = null
): BaseModel(name), Parcelable