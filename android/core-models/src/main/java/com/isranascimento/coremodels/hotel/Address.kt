package com.isranascimento.coremodels.hotel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val state: String,
    val city: String
): Parcelable
