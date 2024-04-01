package br.com.gstoduto.starwars.ui.use_case.movie

interface RemoveMovieFromMyListUseCase {
    suspend fun removeMovieFromMyList(id: String)
}