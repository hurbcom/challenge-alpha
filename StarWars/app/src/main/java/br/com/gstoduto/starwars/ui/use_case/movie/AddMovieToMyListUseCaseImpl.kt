package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.repositories.MovieRepository
import javax.inject.Inject

class AddMovieToMyListUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): AddMovieToMyListUseCase {
    override suspend fun addMovieToMyList(id: String) {
        return repository.addToMyList(id)
    }
}