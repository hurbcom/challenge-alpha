package com.barreto.android.data.content

import com.barreto.android.data.content.local.IContentLocalData
import com.barreto.android.data.content.remote.IContentRemoteData
import com.barreto.android.data.remote.parseRemoteError
import com.barreto.android.domain.base.BaseListModel
import com.barreto.android.domain.base.toSingleError
import com.barreto.android.domain.content.IContentRepository
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Completable
import io.reactivex.Single

class ContentRepository(
    private val remoteData: IContentRemoteData,
    private val localData: IContentLocalData
) : IContentRepository {

    override fun getContentList(queryOptions: HashMap<String, Any>): Single<BaseListModel<ContentItem>> {

        return Single.concat(
            localData.getContentList(queryOptions),
            remoteData.fetchContentList(queryOptions)
        )
            .filter { it.items != null }
            .firstOrError()
            .onErrorResumeNext { it.toSingleError(parseRemoteError(it), it.message) }
    }

    override fun getContentItem(contentItemId: String): Single<ContentItem> {
        return localData.getContentItem(contentItemId)
            .onErrorResumeNext { it.toSingleError() }
    }

    override fun getFavoriteContentList(): Single<List<ContentItem>> {
        return localData.getFavoriteContentList()
    }

    override fun addContentItem(contentItem: ContentItem): Completable {
        return localData.insertContentItem(contentItem)
    }

    override fun deleteContentItem(contentItem: ContentItem): Completable {
        return localData.removeContentItem(contentItem)
    }
}