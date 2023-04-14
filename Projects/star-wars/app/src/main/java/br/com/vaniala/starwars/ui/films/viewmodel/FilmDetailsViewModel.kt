package br.com.vaniala.starwars.ui.films.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.usecase.FavoriteFilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val favoriteFilmUseCase: FavoriteFilmUseCase,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _isFavorite = MutableStateFlow(savedStateHandle.get<Boolean>("isFavorite") ?: false)
    val isFavorite: StateFlow<Boolean>
        get() = _isFavorite

    fun favorite(film: Film) = viewModelScope.launch {
        film.isFavorite = !film.isFavorite
        _isFavorite.emit(film.isFavorite)
        favoriteFilmUseCase(film)
    }

    init {
        savedStateHandle.getStateFlow("isFavorite", false).onEach { isFavorite ->
            _isFavorite.value = isFavorite
        }.launchIn(viewModelScope)
    }
}
