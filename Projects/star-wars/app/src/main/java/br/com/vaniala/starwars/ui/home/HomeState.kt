package br.com.vaniala.starwars.ui.home

import br.com.vaniala.starwars.domain.model.Category

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
sealed class HomeState {
    object Loading : HomeState()
    data class Success(val categories: List<Category>) : HomeState()
    data class Error(val message: String) : HomeState()
}
