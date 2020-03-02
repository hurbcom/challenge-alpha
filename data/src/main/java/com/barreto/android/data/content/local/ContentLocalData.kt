package com.barreto.android.data.content.local

import com.barreto.android.data.content.local.entity.ContentItemDB
import com.barreto.android.data.database.AppDataBase
import com.barreto.android.domain.base.BaseListModel
import com.barreto.android.domain.base.BaseThrowable
import com.barreto.android.domain.content.ContentItemError
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Completable
import io.reactivex.Single

class ContentLocalData(
    private var database: AppDataBase
) : IContentLocalData {

    override fun getContentList(queryOptions: HashMap<String, Any>): Single<BaseListModel<ContentItem>> {
        return Single.just(BaseListModel())
    }

    override fun getContentItem(contentItemId: String): Single<ContentItem> {

        return Single.create {

            database.contentItemDao().getContentItem(contentItemId)?.let { contentItem ->
                it.onSuccess(contentItem.toContentItem())

            } ?: it.onError(BaseThrowable(ContentItemError.CONTENT_NOT_FOUND))
        }
    }

    override fun insertContentItem(contentItem: ContentItem): Completable {

        return Completable.create {
            database.contentItemDao().getContentItem(contentItem.id)?.let { contentItem ->
                it.onError(BaseThrowable(ContentItemError.CONTENT_ALREADY_EXISTS))

            } ?: database.contentItemDao().insertUser(ContentItemDB.fromContentItem(contentItem))

            it.onComplete()
        }
    }

    override fun removeContentItem(contentItem: ContentItem): Completable {

        return Completable.create {
            database.contentItemDao().deleteUser(ContentItemDB.fromContentItem(contentItem))
            it.onComplete()
        }
    }

    override fun getFavoriteContentList(): Single<List<ContentItem>> {

        return Single.create {
            database.contentItemDao().getFavoriteContentList()?.let { content ->
                it.onSuccess(content.map { item ->
                    item.toContentItem()
                })
            } ?: emptyList<ContentItem>()
        }
    }
}