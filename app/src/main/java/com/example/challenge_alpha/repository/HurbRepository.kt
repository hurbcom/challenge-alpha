package com.example.challenge_alpha.repository

import com.example.challenge_alpha.api.GetResult.getResult
import com.example.challenge_alpha.api.GetResult.resultLiveData
import com.example.challenge_alpha.api.GetResult.suggestionsLiveData
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.testing.OpenForTesting
import okio.Source
import javax.inject.Inject
import javax.inject.Singleton

/**
 * classe responsável pela obtenção de dados nos servidores Hurb.
 */
@OpenForTesting
@Singleton
class HurbRepository @Inject constructor(
    private val call: HurbService,
    private val save: ResultDetailRepository
)  {
    private var lastRequestedPage = 0
    private var query = ""

    fun search(queryString: String) {
        lastRequestedPage++
        query = queryString
    }

    fun searchResult() = resultLiveData(
        networkCall = { getResult { call.search(query, lastRequestedPage) } },
        saveSearched = { save.insertDetail(it) })

    fun suggestionSearch(suggestion: String) = suggestionsLiveData(
        networkCall = {  getResult { call.suggestion(suggestion) } })


}