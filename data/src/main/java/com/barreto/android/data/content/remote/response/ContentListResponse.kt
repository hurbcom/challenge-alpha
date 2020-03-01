package com.barreto.android.data.content.remote.response

import com.barreto.android.data.base.BaseListResponse
import com.barreto.android.domain.content.model.ContentItem

class ContentListResponse(

    var meta: Meta? = null,

    var results: List<ContentItemApi> = emptyList()

) : BaseListResponse<ContentItem>(total = meta?.count ?: 0) {

    override fun parseItems(): List<ContentItem> {

        return results.map { it.toContent() }
    }
}

class Meta (
    var count: Int = 0,
    var offset: Int = 0
)