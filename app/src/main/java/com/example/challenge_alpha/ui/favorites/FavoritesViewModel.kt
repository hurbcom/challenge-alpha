package com.example.challenge_alpha.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.challenge_alpha.db.favorites.FavoritesDataBase
import com.example.challenge_alpha.db.resultDetail.ResultDetailDataBase
import com.example.challenge_alpha.model.DBType
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(favoritesRepository :FavoritesRepository) : ViewModel() {

    val getFavorites= favoritesRepository.getFavorites()

}