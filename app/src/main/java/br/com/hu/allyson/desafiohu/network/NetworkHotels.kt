package br.com.hu.allyson.desafiohu.network


import br.com.hu.allyson.desafiohu.domain.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkHotels {

    interface HotelsRepositoryImpl{
        fun request(callback: retrofit2.Callback<Result>)
    }

    interface HotelsSearchRequestImpl{
        @GET("api")
        fun getHotels(@Query("q") search_city:String): retrofit2.Call<Result>
    }

}