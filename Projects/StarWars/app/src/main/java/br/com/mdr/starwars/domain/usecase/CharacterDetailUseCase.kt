package br.com.mdr.starwars.presentation.characters.detail

import br.com.mdr.starwars.domain.repository.CharacterRepository

class CharacterDetailUseCase(
    private val repository: CharacterRepository
) {
    suspend fun getSelectedCharacter(name: String) =
        repository.getCharacter(name)

    suspend fun setFavorite(isFavorite: Boolean, name: String) {
        repository.setFavorite(isFavorite, name)
    }
}