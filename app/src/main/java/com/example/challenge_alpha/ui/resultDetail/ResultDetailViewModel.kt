package com.example.challenge_alpha.ui.resultDetail

import android.app.Application
import androidx.lifecycle.*
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.db.ResultDetailDataBase
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultDetailViewModel(application: Application) : AndroidViewModel(application) {


    val resultDetailDao = ResultDetailDataBase.getInstance(application).resultDetailDao()
    val resultRepository = ResultRepository(resultDetailDao)

    private val _getResult = MutableLiveData<ResultDetailRelation>()
    val getResult = _getResult

    fun getResult(sku: String) = viewModelScope.launch {
        getResult.postValue(resultRepository.getResult(sku))
    }

}