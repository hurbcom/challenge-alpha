package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.domain.model.People
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class FavoriteCharacterUseCase @Inject constructor(
    private val localCharacterDataSource: LocalDataSource.Characters,
) {

    suspend operator fun invoke(character: People) =
        character.name.let {
            localCharacterDataSource.updateIsFavorite(isFavorite = character.isFavorite, name = it)
        }
}
