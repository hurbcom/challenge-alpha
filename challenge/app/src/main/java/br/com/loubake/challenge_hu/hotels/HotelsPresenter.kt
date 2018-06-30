package br.com.loubake.challenge_hu.hotels

import android.content.Context
import android.widget.Toast
import br.com.loubake.challenge_hu.data.ApiService
import br.com.loubake.challenge_hu.data.HotelResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelsPresenter(var context: Context, var mHotelsView: HotelsContract.View) : HotelsContract.Presenter {

    override fun loadHotels() {
        val query = "Rio de Janeiro"
        val service = ApiService(context)
        service.getHotelsApi().listHotels(query).enqueue(object : Callback<HotelResults> {
            override fun onResponse(call: Call<HotelResults>?, response: Response<HotelResults>?) {
                mHotelsView.setHotelsList((response?.body() as HotelResults).hotels)
                mHotelsView.hideLoading()
            }

            override fun onFailure(call: Call<HotelResults>?, t: Throwable?) {
                mHotelsView.showErrorLayout()
                mHotelsView.hideLoading()
            }
        })
    }
}