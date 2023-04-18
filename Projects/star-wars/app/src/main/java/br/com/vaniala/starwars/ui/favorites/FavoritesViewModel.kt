package br.com.vaniala.starwars.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.domain.model.Favorite
import br.com.vaniala.starwars.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
) : ViewModel() {
    private val _favorites = MutableStateFlow<State<Favorite>>(State.Loading)
    val favorites: StateFlow<State<Favorite>>
        get() = _favorites

    fun favorites() = viewModelScope.launch {
        getFavoritesUseCase().collectLatest {
            _favorites.emit(it)
        }
    }
}
