package br.com.gstoduto.starwars.ui.use_case.movie

import br.com.gstoduto.starwars.database.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface GetMovieUseCase {
    suspend fun findMovie(id: String): Flow<MovieEntity>
}