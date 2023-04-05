package com.example.starwars.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory private constructor() : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*,*>? {
        if (getRawType(returnType) != Flow::class.java) {
            return null
        }
        check(returnType is ParameterizedType)
        val responseType = getParameterUpperBound(0, returnType)
        check(responseType is ParameterizedType)

        return ResponseCallAdapter<Any>(
            getParameterUpperBound(
                0,
                responseType
            )
        )
    }

    companion object {
        @JvmStatic
        fun create() = FlowCallAdapterFactory()
    }
}