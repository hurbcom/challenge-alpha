package com.example.challenge_alpha.ui.results

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.db.resultDetail.ResultDetailDataBase
import com.example.challenge_alpha.model.DBType
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultsViewModel(
    private val hurbRepository: HurbRepository,
    private val queryString: String
) : ViewModel() {


    private val _progressBar = MutableLiveData<Boolean>(true)
    val progressBar: LiveData<Boolean> = _progressBar


    val resultsDetailLive = hurbRepository.searchResult
    /*
    val resultsDetailLive = Transformations.map(_resultsDetailLive) { result ->
        val sorted = result.data?.resultDetail?.sortedBy { it.stars }
        sorted.let { list ->
            for (stars in 1..5) {
                list?.firstOrNull { it.stars == stars.toFloat() }?.recyclerTitle = true
            }
        }
        sorted
    }

     */


    init {

        hurbRepository.search(queryString)

    }

/*
    fun search(query: String) = viewModelScope.launch(Dispatchers.IO) {

        queryString.postValue(query)
        val response = hurbRepository.search(query)
        val sorted = response.resultDetail.sortedByDescending { it.stars }
        sorted.let { list ->
            for (stars in 1..5) {
                list.firstOrNull { it.stars == stars.toFloat() }?.recyclerTitle = true
            }
        }

        Log.d(TAG, "$sorted")

        _resultDetailLive.postValue(sorted)
        resultRepository.insertDetail(response)
    }

 */


    fun progressBar(visible: Boolean) {
        _progressBar.value = visible
    }
}