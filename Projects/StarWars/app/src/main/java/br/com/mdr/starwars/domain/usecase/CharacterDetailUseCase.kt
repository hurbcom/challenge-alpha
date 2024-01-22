package br.com.mdr.starwars.domain.usecase

import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.repository.CharacterRepository

class CharacterDetailUseCase(
    private val repository: CharacterRepository
) {
    suspend fun getSelectedCharacter(name: String) =
        repository.getCharacter(name)
    suspend fun setFavorite(isFavorite: Boolean, name: String) {
        repository.setFavorite(isFavorite, name)
    }
    suspend fun saveLastSeen(character: Character) {
        repository.saveLastSeen(character)
    }
}
