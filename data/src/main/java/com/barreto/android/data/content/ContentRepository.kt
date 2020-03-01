package com.barreto.android.data.content

import com.barreto.android.data.content.local.IContentLocalData
import com.barreto.android.data.content.remote.IContentRemoteData
import com.barreto.android.data.remote.parseRemoteError
import com.barreto.android.domain.base.BaseListModel
import com.barreto.android.domain.base.toSingleError
import com.barreto.android.domain.content.IContentRepository
import com.barreto.android.domain.content.model.ContentItem
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

}