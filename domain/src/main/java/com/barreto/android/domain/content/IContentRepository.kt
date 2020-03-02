package com.barreto.android.domain.content

import com.barreto.android.domain.base.BaseListModel
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Completable
import io.reactivex.Single

interface IContentRepository {

    fun getContentList(queryOptions: HashMap<String, Any>): Single<BaseListModel<ContentItem>>

    fun getContentItem(contentItemId: String): Single<ContentItem>

    fun getFavoriteContentList(): Single<List<ContentItem>>

    fun addContentItem(contentItem: ContentItem): Completable

    fun deleteContentItem(contentItem: ContentItem): Completable
}