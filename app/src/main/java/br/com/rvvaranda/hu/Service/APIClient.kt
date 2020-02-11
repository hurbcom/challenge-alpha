package br.com.rvvaranda.hu.Service

import br.com.rvvaranda.hu.Model.Meta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    @GET("search/api")
    fun getHotels(@Query("q") local: String, @Query("page") page: Int) : Call<Meta>
}