package br.com.gstoduto.starwars.ui.use_case.movie

interface AddMovieToMyListUseCase {
    suspend fun addMovieToMyList(id: String)
}