package br.com.gstoduto.starwars.ui.viewmodels.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.ui.uistates.movie.MovieUiState
import br.com.gstoduto.starwars.ui.use_case.movie.GetMoviesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCaseImpl
) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<MovieUiState>(
        MovieUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        loadUiState()
    }

    private fun loadUiState() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            getMoviesUseCase.findMovies().onStart {
                _uiState.update { MovieUiState.Loading }
            }.collectLatest { movies ->
                if (movies.isEmpty()) {
                    _uiState.update {
                        MovieUiState.Empty
                    }
                } else {
                    _uiState.update {
                        MovieUiState.Success(
                            movies = movies
                        )
                    }
                }
            }
        }
    }

    fun loadMovies() {
        loadUiState()
    }
}