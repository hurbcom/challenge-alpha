package com.barreto.android.data.content.local

import com.barreto.android.domain.base.BaseListModel
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Single

interface IContentLocalData {

    fun getContentList(queryOptions: HashMap<String, Any>): Single<BaseListModel<ContentItem>>

}