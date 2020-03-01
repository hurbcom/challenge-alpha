package com.barreto.android.domain.base

interface IError {

    companion object {
        val GENERIC_ERROR = object : IError {
        }
    }
}
