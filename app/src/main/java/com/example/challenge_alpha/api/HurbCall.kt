package com.example.challenge_alpha.api

import android.util.Log
import com.example.challenge_alpha.model.Filters
import com.example.challenge_alpha.model.Header
import com.example.challenge_alpha.model.ResultDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val TAG = "HurbCall"


fun hurbCall(
    service: HurbService,
    query: String,
    page: Int,
    onSuccess: (resultDetail: List<ResultDetail>, filters: Filters, header: Header) -> Unit,
    onError: (error: String) -> Unit
) {
    Log.d(TAG, "query: $query, page: $page")

    val apiQuery = query

    service.searchRepos(apiQuery, page).enqueue(
        object : Callback<HurbResponse> {
            override fun onFailure(call: Call<HurbResponse>?, t: Throwable) {
                onError(t.message ?: "unknown error")
                Log.d(TAG, "fail to get data ${t.message}")
            }

            override fun onResponse(
                call: Call<HurbResponse>?,
                response: Response<HurbResponse>
            ) {
                Log.d(TAG, "got a response $response")
                if (response.isSuccessful) {
                    val resultDetail = response.body()?.resultDetail ?: emptyList()
                    val filters = response.body()?.filters ?: Filters()
                    val header = response.body()?.meta?: Header()

                    onSuccess(resultDetail, filters, header)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                    Log.d(TAG, "fail to get data2 ${response.errorBody()?.string()}")
                }
            }
        }
    )
}

interface HurbService {

    @GET("search/api?")
    fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Call<HurbResponse>

    companion object {
        private const val BASE_URL = "https://www.hurb.com/"

        fun create(): HurbService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HurbService::class.java)
        }
    }
}