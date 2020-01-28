package com.example.challenge_alpha.ui.resultDetail

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import com.example.challenge_alpha.testing.OpenForTesting
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
@OpenForTesting
class ResultDetailViewModel @AssistedInject constructor(
    private val lastSeenRepository: LastSeenRepository,
    private val favoritesRepository: FavoritesRepository,
    private val resultDetailRepository: ResultDetailRepository,
    @Assisted private val sku: String
) : ViewModel() {


    @AssistedInject.Factory
    interface Factory {
        fun create(sku: String): ResultDetailViewModel
    }

    private fun isFavoritedLive() = favoritesRepository.isFavorited(sku)
    fun isFavorited () = Transformations.map(isFavoritedLive()) { !it?.sku.isNullOrEmpty() }

    private fun getResultLive() = resultDetailRepository.getResult(sku)
    fun getResult() = Transformations.map(getResultLive()) {
        viewModelScope.launch {
            lastSeenRepository.insertDetailRelation(it)
        }
        it
    }


    fun insertFavorite() = viewModelScope.launch {
        favoritesRepository.insertFavorite(getResultLive().value!!)
    }

    fun deleteFavorite() = viewModelScope.launch {
        favoritesRepository.deleteFavorite(getResultLive().value?.resultDetail?.sku!!)
    }

}