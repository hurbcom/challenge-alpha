package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend fun findMovies(): Flow<List<Movie>>
}