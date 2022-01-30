package com.br.natanbrito.challenge.data.model.results

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuantityDescriptors(
    val maxAdults: Int,
    val maxChildren: Int,
    val maxFreeChildrenAge: Int
) : Parcelable
