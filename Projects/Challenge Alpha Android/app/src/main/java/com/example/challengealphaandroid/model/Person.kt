package com.example.challengealphaandroid.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.challengealphaandroid.common.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "people")
data class Person(
    @PrimaryKey
    @NonNull
    override val name: String,
    val birthYear: String?,
    @Embedded val homeworld: Homeworld,
    val id: Int,
): BaseModel(name), Parcelable

@Parcelize
data class Homeworld(
    val homeName: String?
): Parcelable

