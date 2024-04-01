package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): GetMovieUseCase {
    override suspend fun findMovie(id: String): Flow<MovieEntity> {
        return repository.findMovie(id)
    }
}