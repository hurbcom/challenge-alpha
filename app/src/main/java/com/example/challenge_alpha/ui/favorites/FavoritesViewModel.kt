package com.example.challenge_alpha.ui.favorites

import androidx.lifecycle.ViewModel
import com.example.challenge_alpha.repository.FavoritesRepository
import javax.inject.Inject

/**
 * ViewModel para tratar os dados necessários do fragmento [FavoritesFragment].
 * as informações são obtidas do [FavoritesRepository] e o fragmento obtêm os dados através de
 * um Observer em [getFavorites]
 */
class FavoritesViewModel @Inject constructor(favoritesRepository :FavoritesRepository) : ViewModel() {

    val getFavorites= favoritesRepository.getFavorites()

}