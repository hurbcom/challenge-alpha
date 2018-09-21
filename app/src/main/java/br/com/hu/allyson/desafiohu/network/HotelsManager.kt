package br.com.hu.allyson.desafiohu.network

import br.com.hu.allyson.desafiohu.BuildConfig
import br.com.hu.allyson.desafiohu.domain.Result
import retrofit2.Callback

class HotelsManager(val search:String): NetworkHotels.HotelsRepositoryImpl{
    override fun request(callback: Callback<Result>) {
        RetrofitService().getService(NetworkHotels.HotelsSearchRequestImpl::class.java, BuildConfig.BASE_URL).getHotels(search).enqueue(callback)
    }

}