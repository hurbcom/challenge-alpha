package br.com.mdr.starwars.ui.presentation.films.detail

import androidx.lifecycle.SavedStateHandle
import br.com.mdr.starwars.common.Constants.FILM_ID_KEY
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.usecase.FilmDetailUseCase
import br.com.mdr.starwars.ui.presentation.base.BaseDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FilmDetailViewModel(
    private val useCase: FilmDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseDetailViewModel() {
    private val _film: MutableStateFlow<Film?> = MutableStateFlow(null)
    val film: StateFlow<Film?> = _film

    init {
        getFilm()
    }

    override suspend fun refresh() {
        getFilm()
    }

    private fun getFilm() {
        val id = _film.value?.id ?: savedStateHandle.get<Int>(FILM_ID_KEY)

        id?.let { filmId ->
            launch(Dispatchers.IO) {
                val film = useCase.getSelectedFilm(filmId)
                _film.emit(film)
                _isFavorite.emit(film.isFilmFavorite)
                useCase.saveLastSeen(film)
            }
        }
    }

    override fun setFavorite(isFavorite: Boolean) {
        _film.value?.let {
            it.isFilmFavorite = isFavorite

            launch(Dispatchers.IO) {
                it.isFilmFavorite.let { it1 -> _isFavorite.emit(it1) }
                _film.emit(it)
                useCase.setFavorite(it.isFilmFavorite, it.id)
            }
        }
    }
}
