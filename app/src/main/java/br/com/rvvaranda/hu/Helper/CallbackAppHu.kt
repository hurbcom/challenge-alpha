package br.com.rvvaranda.hu.Helper

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface CallbackAppHu<T> {

    fun onComplete(result: T)

    suspend fun <T> awaitCallBack(block: (CallbackAppHu<T>) -> Unit): T =
        suspendCoroutine { cont ->
            block(object : CallbackAppHu<T> {
                override fun onComplete(result: T) = cont.resume(result)
            })
        }
}