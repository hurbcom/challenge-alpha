package com.barreto.android.data.content.remote

import io.reactivex.Single
import com.barreto.android.data.content.remote.api.IContentApiClient
import com.barreto.android.domain.base.BaseListModel
import com.barreto.android.domain.content.model.ContentItem

class ContentRemoteData(
        private val apiClient: IContentApiClient
) : IContentRemoteData {

    override fun fetchContentList(queryOptions: HashMap<String, Any>): Single<BaseListModel<ContentItem>> {

        return apiClient.fetchContentList(queryOptions)
                .map { list ->

                    list.toListModel()
                }
    }

}