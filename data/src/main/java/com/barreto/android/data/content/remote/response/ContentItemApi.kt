package com.barreto.android.data.content.remote.response

import android.net.Uri
import com.barreto.android.domain.content.model.ContentItem
import com.squareup.moshi.Json

class ContentItemApi(

    var id: String = "",

    var sku: String = "",

    var name: String = "",

    var image: String? = null,

    var description: String = "",

    var stars: Int? = null,

    var isHotel: Boolean? = null,

    var isPackage: Boolean? = null,

    var smallDescription: String = ""

) {

    fun toContent(): ContentItem {

        return ContentItem(
            id,
            sku,
            name,
            image,
            description,
            stars ?: 0,
            isHotel ?: false,
            isPackage ?: false,
            smallDescription
        )
    }
}