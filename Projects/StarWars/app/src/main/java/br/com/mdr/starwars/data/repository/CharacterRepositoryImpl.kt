package br.com.mdr.starwars.data.repository

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.repository.CharacterRepository
import br.com.mdr.starwars.domain.repository.LocalDataSource
import br.com.mdr.starwars.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val dataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): CharacterRepository {

    override fun getCharacters(): Flow<PagingData<Character>> =
        dataSource.getAllCharacters()

    override fun searchCharacters(query: String): Flow<PagingData<Character>> =
        dataSource.searchCharacters(query)

    override suspend fun getCharacter(name: String): Character =
        localDataSource.getSelectedCharacter(name)

    override suspend fun setFavorite(isFavorite: Boolean, name: String) {
        localDataSource.setFavoriteCharacter(isFavorite, name)
    }
}
