package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.repositories.MovieRepository
import javax.inject.Inject

class RemoveMovieFromMyListUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): RemoveMovieFromMyListUseCase {
    override suspend fun removeMovieFromMyList(id: String) {
        return repository.removeFromMyList(id)
    }
}