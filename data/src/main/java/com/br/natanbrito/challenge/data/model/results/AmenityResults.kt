package com.br.natanbrito.challenge.data.model.results

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AmenityResults(
    val category: String,
    val name: String
) : Parcelable
