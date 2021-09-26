package com.filipeoliveira.hurbchallenge.ui.model

import com.google.gson.annotations.SerializedName

data class QuantityDescriptorsUI(
    val maxChildren: Int = 0,
    val maxAdults: Int = 0,
    val maxFreeChildrenAge: Int = 0
)