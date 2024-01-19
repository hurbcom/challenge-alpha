package br.com.mdr.starwars.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mdr.starwars.domain.model.Character

@Dao
interface CharactersDao {

    @Query("select count(name) from character_table")
    fun getCount(): Int

    @Query("select * from character_table")
    fun getCharacters(): PagingSource<Int, Character>

    @Query("select * from character_table WHERE name=:name")
    fun getSelectedCharacter(name: String): Character

    @Query("SELECT * FROM character_table WHERE name LIKE '%' || :query || '%'")
    fun getCharactersByTitle(query: String): PagingSource<Int, Character>

    @Query("UPDATE character_table SET isFavorite = :isFavorite WHERE name = :name")
    suspend fun updateIsFavorite(isFavorite: Boolean, name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Character>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacters()
}