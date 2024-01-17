package br.com.mdr.starwars.domain.usecase

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow

class FilmUseCase(
    private val repository: FilmRepository
) {

    fun getFilms(query: String): Flow<PagingData<Film>> =
        if (query.isEmpty()) {
            repository.getFilms()
        } else {
            repository.searchFilms(query)
        }
}