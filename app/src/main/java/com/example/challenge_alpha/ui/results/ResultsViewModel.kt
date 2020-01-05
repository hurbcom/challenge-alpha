package com.example.challenge_alpha.ui.results

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.db.ResultDetailDataBase
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ResultsViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "resultsDB"

    val resultDetailDao = ResultDetailDataBase.getInstance(application).resultDetailDao()
    val resultRepository = ResultRepository(resultDetailDao)
    private val hurbRepository = HurbRepository(HurbService.create())

    private val queryString = MutableLiveData<String>()
    private var searchComplete = false

    private val _progressBar = MutableLiveData<Boolean>(true)
    val progressBar: LiveData<Boolean> = _progressBar

    private val _resultDetailLive = MutableLiveData<List<ResultDetail>>()
    val resultDetailLive : LiveData<List<ResultDetail>> = _resultDetailLive

    fun search(query: String) = viewModelScope.launch(Dispatchers.IO) {
        if (searchComplete) return@launch

        queryString.postValue(query)
        val response = hurbRepository.search(query)
        _resultDetailLive.postValue(response.resultDetail.sortedByDescending { it.stars })

        response.resultDetail.forEach {
            resultRepository.insertDetail(it)
            resultRepository.insertAmenities(it.amenities.map { list ->
                ResultDetailAmenities(it.sku, list.name, list.category)
            })
        }
        searchComplete = true
    }


    fun progressBar(visible: Boolean) {
        _progressBar.value = visible
    }
}