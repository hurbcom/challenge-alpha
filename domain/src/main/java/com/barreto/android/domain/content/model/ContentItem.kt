package com.barreto.android.domain.content.model

import java.io.Serializable

data class ContentItem(

        var id: String = "",

        var sku: String = "",

        var name: String = "",

        var image: String? = "",

        var description: String = "",

        var stars: Int = 0,

        var isHotel: Boolean = false,

        var isPackage: Boolean = false,

        var smallDescription: String = ""

) : Serializable