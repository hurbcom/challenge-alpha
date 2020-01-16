package com.ayodkay.alpha.challenge.viewmodel

import android.widget.Toast
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

    fun getHotels(): LiveData<JSONObject>
    {
        if (hotelsJson == null) {
            hotelsJson = MutableLiveData()
            loadHotels()
        }
        return hotelsJson!!
    }

    // Do an asynchronous operation to fetch hotels.
    private fun loadHotels()
    {
        val queue = Volley.newRequestQueue(appContext)
        val url = "https://www.hurb.com/search/api?q=buzios&page=1"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener
            { response ->
                hotelsJson?.value = response!!

            },

            Response.ErrorListener
            { error ->
                Toast.makeText(appContext,error.message,Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

    }
}