package com.example.challenge_alpha.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.db.ResultDetailDao
import com.example.challenge_alpha.model.Filters
import com.example.challenge_alpha.model.Header
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import retrofit2.Retrofit

private const val TAG = "HurbCall"

class HurbRepository(
    private val call: HurbService
) {

    private var lastRequestedPage = 1

    suspend fun search(query: String): HurbResponse {

        Log.d(TAG, "New query: $query")
        val response = call.searchRepos(query, lastRequestedPage)
        lastRequestedPage++
        return response


    }


}
