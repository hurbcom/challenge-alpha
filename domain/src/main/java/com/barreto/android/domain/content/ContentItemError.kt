package com.barreto.android.domain.content

import com.barreto.android.domain.base.IError

enum class ContentItemError : IError{
    CONTENT_NOT_FOUND,
    CONTENT_ALREADY_EXISTS
}