package br.com.rvvaranda.hu.Repository

import android.util.Log
import br.com.rvvaranda.hu.Model.Meta
import br.com.rvvaranda.hu.Service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HotelsRepository {

    fun getHotels(page: Int, responseData: (success: Boolean, payload: Meta?) -> Unit) {

        try {
            APIService.apiInstance.getHotels(local = "buzios",  page = page).enqueue(object : Callback<Meta> {

                override fun onFailure(call: Call<Meta>?, t: Throwable) {
                    responseData(false, null)
                }

                override fun onResponse(call: Call<Meta>?, response: Response<Meta>?) {
                    if (response!= null && response.isSuccessful) {
                        responseData(true, response.body()!!)
                    } else {
                        responseData(false, null)
                    }
                }
            })
        } catch (ex: Exception) {
            Log.e("Hurb", ex.message)
        }
    }
}