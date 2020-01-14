package com.ayodkay.alpha.challenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ayodkay.alpha.challenge.App.Companion.appContext
import org.json.JSONObject

class HotelViewModel: ViewModel() {


    private var hotelsJson: MutableLiveData<JSONObject>? = null

    fun getHotels(): LiveData<JSONObject> {
        if (hotelsJson == null) {
            hotelsJson = MutableLiveData()
            loadHotels()
        }
        return hotelsJson!!
    }


    private fun loadHotels(){
        val queue = Volley.newRequestQueue(appContext)
        val url = "https://www.hurb.com/search/api?q=buzios&page=1"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                hotelsJson?.value = response!!

            },
            Response.ErrorListener { error ->
                Log.d("volley response", error.toString())
            }
        )
        queue.add(jsonObjectRequest)

    }
}