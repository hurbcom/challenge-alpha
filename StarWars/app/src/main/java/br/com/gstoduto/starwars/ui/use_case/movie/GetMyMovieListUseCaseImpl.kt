package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyMovieListUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): GetMyMovieListUseCase {
    override suspend fun getMyListMovies(): Flow<List<MovieEntity>> {
        return repository.myList()
    }
}