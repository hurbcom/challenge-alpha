package com.edufelip.challengealpha.data.network.base

import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException

internal suspend fun <T : Any> unsafeApiCall(call: suspend () -> T): T {
    return coroutineScope {
        try {
            call()
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    throw Throwable("There was a network problem")
                }
                is HttpException -> {
                    throw Throwable(throwable.message())
                }
                else -> {
                    throw throwable
                }
            }
        }
    }
}
