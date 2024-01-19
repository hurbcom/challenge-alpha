package br.com.mdr.starwars.ui.presentation.films.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.mdr.starwars.common.Constants.FILM_ID_KEY
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.usecase.FilmDetailUseCase
import br.com.mdr.starwars.ui.presentation.base.BaseDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val useCase: FilmDetailUseCase,
    private val savedStateHandle: SavedStateHandle
): br.com.mdr.starwars.ui.presentation.base.BaseDetailViewModel() {
    private val _film: MutableStateFlow<Film?> = MutableStateFlow(null)
    val film: StateFlow<Film?> = _film

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        getFilm()
    }

    override suspend fun refresh() {
        getFilm()
    }

    private fun getFilm() {
        val id = _film.value?.id ?: savedStateHandle.get<Int>(FILM_ID_KEY)

        id?.let { filmId ->
            viewModelScope.launch(Dispatchers.IO) {
                val film = useCase.getSelectedFilm(filmId)
                _film.emit(film)
                _isFavorite.emit(film.isFavorite)
            }
        }
    }

    override fun setFavorite() {
        _film.value?.let {
            it.isFavorite = !it.isFavorite

            viewModelScope.launch(Dispatchers.IO) {
                _isFavorite.emit(it.isFavorite)
                _film.emit(it)
                useCase.setFavorite(it.isFavorite, it.id)
            }
        }
    }
}