package com.example.challenge_alpha.ui.favorites

import androidx.lifecycle.ViewModel
import com.example.challenge_alpha.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(favoritesRepository :FavoritesRepository) : ViewModel() {

    val getFavorites= favoritesRepository.getFavorites()

}