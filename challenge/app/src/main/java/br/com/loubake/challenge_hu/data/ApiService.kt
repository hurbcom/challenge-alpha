package br.com.loubake.challenge_hu.data

import android.content.Context
import br.com.loubake.challenge_hu.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService(context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getHotelsApi() : HotelsService {
        return retrofit.create(HotelsService::class.java)
    }
}