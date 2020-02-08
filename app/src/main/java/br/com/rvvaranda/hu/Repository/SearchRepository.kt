package br.com.rvvaranda.hu.Repository

import android.util.Log
import br.com.rvvaranda.hu.Helper.CallbackAppHu
import br.com.rvvaranda.hu.Service.APIService
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class SearchRepository {

    fun LoadHotels(page: Int, callback: CallbackAppHu<String>) {

        APIService.get("api?q=buzios&page=$page", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 200) {

                    val jsonResult = response.body.let { response.body?.string() } ?: ""
                    callback.onComplete(jsonResult)
                }
            }
        })
    }
}