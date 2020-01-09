package com.example.challenge_alpha.ui.resultDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.challenge_alpha.db.favorites.FavoritesDataBase
import com.example.challenge_alpha.db.resultDetail.ResultDetailDataBase
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.db.lastSeen.LastSeenDao
import com.example.challenge_alpha.db.lastSeen.LastSeenDataBase
import com.example.challenge_alpha.model.DBType
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import com.example.challenge_alpha.ui.results.ResultsViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultDetailViewModel @AssistedInject constructor(
    lastSeenRepository: LastSeenRepository,
    private val favoritesRepository: FavoritesRepository,
    resultDetailRepository: ResultDetailRepository,
    @Assisted sku: String
) : ViewModel() {


    @AssistedInject.Factory
    interface Factory {
        fun create(sku: String): ResultDetailViewModel
    }

    private val _getResult = resultDetailRepository.getResult(sku)
    val getResult = Transformations.map(_getResult) {
        viewModelScope.launch {
            lastSeenRepository.insertDetailRelation(it)
        }

        it
    }

    private val _isFavorited = favoritesRepository.isFavorited(sku)
    val isFavorited = Transformations.map(_isFavorited) { !it?.sku.isNullOrEmpty() }


    fun insertFavorite() = viewModelScope.launch {
        favoritesRepository.insertFavorite(getResult.value!!)
    }

    fun deleteFavorite() = viewModelScope.launch {
        favoritesRepository.deleteFavorite(getResult.value?.resultDetail?.sku!!)
    }

}