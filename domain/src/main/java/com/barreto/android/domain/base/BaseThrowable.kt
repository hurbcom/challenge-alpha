package com.barreto.android.domain.base

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import com.barreto.android.domain.base.IError.Companion.GENERIC_ERROR

class BaseThrowable(
    val type: IError = IError.GENERIC_ERROR,

    message: String? = null,

    cause: Throwable? = null
) : Throwable(message, cause)

fun Throwable.toBaseThrowable(type: IError = GENERIC_ERROR, message: String? = null): BaseThrowable {
    return when (this) {
        is BaseThrowable -> this
        else -> BaseThrowable(type = type, message = message, cause = this)
    }
}

fun <T> Throwable.toSingleError(type: IError = GENERIC_ERROR, message: String? = null): Single<T> =
        Single.error(toBaseThrowable(type, message))

fun Throwable.toCompletableError(type: IError = GENERIC_ERROR, message: String? = null): Completable =
        Completable.error(toBaseThrowable(type, message))

fun <T> Throwable.toObservableError(type: IError = GENERIC_ERROR, message: String? = null): Observable<T> =
        Observable.error(toBaseThrowable(type, message))

fun <T> Throwable.toFlowableError(type: IError = GENERIC_ERROR, message: String? = null): Flowable<T> =
        Flowable.error(toBaseThrowable(type, message))
