package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.database.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface GetMyMovieListUseCase {
    suspend fun getMyListMovies(): Flow<List<MovieEntity>>
}