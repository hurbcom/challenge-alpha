package com.barreto.android.data.remote

import com.barreto.android.domain.base.IError

enum class RemoteError : IError {

    UNKNOW_HOST,
    UNAUTHORIZED
}