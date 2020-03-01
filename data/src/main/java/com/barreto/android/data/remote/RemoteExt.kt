package com.barreto.android.data.remote

import com.barreto.android.data.remote.RemoteError.UNAUTHORIZED
import com.barreto.android.data.remote.RemoteError.UNKNOW_HOST
import com.barreto.android.domain.base.IError
import retrofit2.HttpException
import java.net.UnknownHostException

fun parseRemoteError(error: Throwable): IError {
    return when {
        error is UnknownHostException -> UNKNOW_HOST
        error is HttpException && error.code() == 401 -> UNAUTHORIZED
        else -> IError.GENERIC_ERROR
    }
}

