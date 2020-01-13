package com.example.challenge_alpha.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.challenge_alpha.api.*
import com.example.challenge_alpha.api.GetResult.getResult
import com.example.challenge_alpha.api.GetResult.resultLiveData
import com.example.challenge_alpha.api.GetResult.suggestionsLiveData
import com.example.challenge_alpha.model.Suggestions
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "HurbCall"

@Singleton
class HurbRepository @Inject constructor(
    private val call: HurbService,
    private val save: ResultDetailRepository
) : IHurbRepository {
    private var lastRequestedPage = 0
    private var query = ""

    override fun search(queryString: String) {
        lastRequestedPage++
        query = queryString
    }

    val searchResult = resultLiveData(
        networkCall = { getResult { call.search(query, lastRequestedPage) } },
        saveSearched = { save.insertDetail(it) })

    override fun suggestionSearch(suggestion: String) = suggestionsLiveData(
        networkCall = {  getResult { call.suggestion(suggestion) } })

}