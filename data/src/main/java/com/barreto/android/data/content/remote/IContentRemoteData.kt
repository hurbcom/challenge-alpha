package com.barreto.android.data.content.remote

import com.barreto.android.domain.base.BaseListModel
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Single

interface IContentRemoteData {

    fun fetchContentList(queryOptions: HashMap<String, Any>): Single<BaseListModel<ContentItem>>

}