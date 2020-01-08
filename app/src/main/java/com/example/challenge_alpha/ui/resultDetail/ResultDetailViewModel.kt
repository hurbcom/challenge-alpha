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
import kotlinx.coroutines.launch

class ResultDetailViewModel(
    private val lastSeenRepository: LastSeenRepository,
    private val favoritesRepository: FavoritesRepository,
    private val resultDetailRepository: ResultDetailRepository,
    private val sku: String
) : ViewModel() {


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