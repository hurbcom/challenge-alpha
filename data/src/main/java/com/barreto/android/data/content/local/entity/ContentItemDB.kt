package com.barreto.android.data.content.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barreto.android.domain.content.model.ContentItem

@Entity(tableName = "contents")
data class ContentItemDB(
    @PrimaryKey
    var id: String = "",

    var sku: String = "",

    var name: String = "",

    var image: String? = "",

    var description: String = "",

    var stars: Int = 0,

    var isHotel: Boolean = false,

    var isPackage: Boolean = false,

    var smallDescription: String = "",

    var price: Float? = null
) {

    fun toContentItem(): ContentItem {
        return ContentItem(
            id,
            sku,
            name,
            image,
            description,
            stars,
            isHotel,
            isPackage,
            smallDescription,
            price
        )
    }

    companion object {

        fun fromContentItem(contentItemModel: ContentItem): ContentItemDB {

            return ContentItemDB(
                contentItemModel.id,
                contentItemModel.sku,
                contentItemModel.name,
                contentItemModel.image,
                contentItemModel.description,
                contentItemModel.stars,
                contentItemModel.isHotel,
                contentItemModel.isPackage,
                contentItemModel.smallDescription,
                contentItemModel.price
            )
        }
    }
}