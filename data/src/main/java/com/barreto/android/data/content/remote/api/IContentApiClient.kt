package com.barreto.android.data.content.remote.api

import io.reactivex.Single
import com.barreto.android.data.content.remote.response.ContentListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IContentApiClient {

    @GET(".")
    fun fetchContentList(@QueryMap queryOptions: HashMap<String, Any>): Single<ContentListResponse>
}