package com.example.challenge_alpha.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.api.hurbCall
import com.example.challenge_alpha.model.Filters
import com.example.challenge_alpha.model.Header
import com.example.challenge_alpha.model.ResultDetail

private const val TAG = "HurbCall"

class HurbRepository(private val call: HurbService) {

    private var lastRequestedPage = 1

    val networkErrors = MutableLiveData<String>()
    val resultDetailLive = MutableLiveData<List<ResultDetail>>()
    val filterLive = MutableLiveData<Filters>()
    val headerLive = MutableLiveData<Header>()

    private var isRequestInProgress = false

    fun search(query: String) {

        Log.d(TAG, "New query: $query")
        lastRequestedPage = 1
        request(query)
    }

    fun requestMore(query: String) {
        request(query)
    }

    private fun request(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        hurbCall(call, query, lastRequestedPage, { resultDetail, filter, header ->

            resultDetailLive.value = resultDetail
            filterLive.postValue(filter)
            headerLive.postValue(header)

            isRequestInProgress = false

        }, { error ->

            networkErrors.postValue(error)
            isRequestInProgress = false

        })
    }
}
