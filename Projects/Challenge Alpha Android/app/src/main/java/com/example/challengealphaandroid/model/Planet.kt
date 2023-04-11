package com.example.challengealphaandroid.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.challengealphaandroid.common.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "planet")
data class Planet(
    @PrimaryKey
    @NonNull
    override val name: String,
    val population: Double?,
    val diameter: Int?,
    var id: Int? = null
): BaseModel(name), Parcelable