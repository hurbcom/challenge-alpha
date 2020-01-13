package com.example.challenge_alpha.ui.resultDetail

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

/**
 * ViewModel para tratar os dados necessários do fragmento [ResultDetailFragment].
 * A viewmodel popula o repositório [LastSeenRepository] de acordo com os dados recebidos [sku]
 * do fragmento [ResultsFragment], ao mesmo tempo que obtem os dados do hotel/pacote do
 * [ResultDetailRepository], através de [getResult]
 *
 * A confirmação se o hotel/pacote á foi adicionado aos favoritos do usuários é obtivo através
 * de [isFavorited], que busca do Room através do [FavoritesRepository]
 *
 */
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