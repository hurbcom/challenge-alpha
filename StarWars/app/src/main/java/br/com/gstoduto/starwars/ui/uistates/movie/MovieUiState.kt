package br.com.gstoduto.starwars.ui.uistates.movie

import br.com.gstoduto.starwars.model.Movie


sealed class MovieUiState {

    object Loading : MovieUiState()

    object Empty : MovieUiState()

    data class Success(
        val movies: List<Movie> = emptyList()
    ) : MovieUiState()

}