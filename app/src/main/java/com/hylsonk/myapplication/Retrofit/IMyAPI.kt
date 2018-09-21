package com.hylsonk.myapplication.Retrofit

import com.hylsonk.myapplication.Model.Result
import com.hylsonk.myapplication.Model.RootObject
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IMyAPI {

    @GET("api")
    fun hitCountCheck(@Query("q") q:String):Observable<RootObject>

    companion object {
        fun create():IMyAPI{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://search.hotelurbano.com/")
                .build()
            return retrofit.create(IMyAPI::class.java)
        }
    }
}