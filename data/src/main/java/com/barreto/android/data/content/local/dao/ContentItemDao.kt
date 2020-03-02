package com.barreto.android.data.content.local.dao

import androidx.room.*
import com.barreto.android.data.content.local.entity.ContentItemDB

@Dao
interface ContentItemDao {

    @Query("SELECT * FROM contents WHERE id = :contentItemId")
    fun getContentItem(contentItemId: String): ContentItemDB?

    @Query("SELECT * FROM contents ORDER BY stars DESC")
    fun getFavoriteContentList(): List<ContentItemDB>?

    @Insert
    fun insertUser(contentItem: ContentItemDB)

    @Delete
    fun deleteUser(contentItem: ContentItemDB)
}