package com.hurb.challengealpha.api

import com.hurb.challengealpha.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HurbApi {
    companion object {
        const val API_URL = "https://www.hurb.com/search/api/"

        private const val QUERY_PARAM = "q"
        private const val PAGE_PARAM = "page"
    }

    @GET(".")
    fun search(
        @Query(QUERY_PARAM) q: String,
        @Query(PAGE_PARAM) page: Int
    ): Observable<SearchResponse>

}