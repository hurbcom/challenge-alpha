package br.com.gstoduto.starwars.ui.uistates.movie

import br.com.gstoduto.starwars.model.Movie

sealed class MyMovieListUiState {

    object Loading : MyMovieListUiState()

    data class Empty(
        val movies: List<Movie> = emptyList()
    ) : MyMovieListUiState()

    data class Success(
        val movies: List<Movie> = emptyList()
    ) : MyMovieListUiState()

}

