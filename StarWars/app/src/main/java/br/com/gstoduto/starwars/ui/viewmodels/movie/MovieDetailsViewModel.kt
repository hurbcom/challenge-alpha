package br.com.gstoduto.starwars.ui.viewmodels.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toMovie
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.ui.uistates.movie.MovieDetailsUiState
import br.com.gstoduto.starwars.ui.use_case.movie.AddMovieToMyListUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.movie.GetMovieUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.movie.RemoveMovieFromMyListUseCaseImpl
import br.com.gstoduto.starwars.util.Constants.ID_MOVIE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieUseCaseImpl: GetMovieUseCaseImpl,
    private val addMovieToMyListUseCase: AddMovieToMyListUseCaseImpl,
    private val removeMovieFromMyListUseCase: RemoveMovieFromMyListUseCaseImpl
) : ViewModel() {

    private val idMovie = savedStateHandle.get<String>(ID_MOVIE)

    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState: StateFlow<MovieDetailsUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            idMovie?.let { loadMovie(it) }
        }
    }

    private suspend fun loadMovie(id: String) {
        id.let {
            val movie = getMovieUseCaseImpl.findMovie(it)

            movie.collect { movieEntity ->
                _uiState.value = _uiState.value.copy(
                    movie = movieEntity.toMovie()
                )
            }
        }
    }

    suspend fun addMovieToMyList(movie: Movie) {
        addMovieToMyListUseCase.addMovieToMyList(movie.id.toString())
    }

    suspend fun removeMovieFromMyList(movie: Movie) {
        removeMovieFromMyListUseCase.removeMovieFromMyList(movie.id.toString())
    }
}
