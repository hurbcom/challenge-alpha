package com.example.challenge_alpha.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.challenge_alpha.db.lastSeen.LastSeenDataBase
import com.example.challenge_alpha.db.resultDetail.ResultDetailDataBase
import com.example.challenge_alpha.model.DBType
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    lastSeenRepository: LastSeenRepository,
    resultDetailRepository: ResultDetailRepository
    ) : ViewModel() {


    val getLastSeen = lastSeenRepository.getLastSeen()

    val getLastSearched = resultDetailRepository.getSearch()

    private val _queryString = MutableLiveData<String>()
    val queryString: LiveData<String> = _queryString

    fun search(query: String) {
        _queryString.postValue(query)
    }


}