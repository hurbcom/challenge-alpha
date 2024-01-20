package br.com.mdr.starwars.domain.usecase

import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.FilmRepository

class FilmDetailUseCase(
    private val repository: FilmRepository
) {
    suspend fun getSelectedFilm(id: Int) =
        repository.getFilm(id)

    suspend fun setFavorite(isFavorite: Boolean, filmId: Int) {
        repository.setFavorite(isFavorite, filmId)
    }

    suspend fun saveLastSeen(film: Film) {
        repository.saveLastSeen(film)
    }
}