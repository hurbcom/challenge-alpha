package com.example.starwars.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ResponseCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<ApiResult<T>>> {

    override fun adapt(call: Call<T>): Flow<ApiResult<T>> {
        return flow {
            emit(ApiResult.Loading)

            emit(suspendCoroutine { continuation ->
                call.enqueue(object : Callback<T> {
                    override fun onFailure(call: Call<T>, t: Throwable) {
                        continuation.resume(ApiResult.Error(t))
                    }

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        continuation.resume(
                            if (response.isSuccessful) {
                                ApiResult.Success(response.body())
                            } else {
                                try {
                                    ApiResult.Error(
                                        Throwable()
                                    )
                                } catch (e: Exception) {
                                    ApiResult.Error(e)
                                }
                            }
                        )
                    }

                })
            })
        }
    }

    override fun responseType() = responseType
}