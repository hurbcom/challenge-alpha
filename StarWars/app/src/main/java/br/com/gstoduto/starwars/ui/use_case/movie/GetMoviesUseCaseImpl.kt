package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): GetMoviesUseCase {
    override suspend fun findMovies(): Flow<List<Movie>> {
        return repository.findMovies()
    }
}