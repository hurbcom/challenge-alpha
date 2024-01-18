package br.com.mdr.starwars.presentation.films.detail

import br.com.mdr.starwars.domain.repository.FilmRepository

class FilmDetailUseCase(
    private val repository: FilmRepository
) {
    suspend fun getSelectedFilm(id: Int) =
        repository.getFilm(id)

    suspend fun setFavorite(isFavorite: Boolean, filmId: Int) {
        repository.setFavorite(isFavorite, filmId)
    }
}