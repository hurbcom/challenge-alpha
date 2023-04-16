package br.com.hurbandroidchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity

@Dao
interface CharactersDao {

    @Insert
    suspend fun insertNewCharacters(characters: List<PeopleEntity>)

    @Update(entity = PeopleEntity::class)
    suspend fun updateCharacter(characters: UpdateEntity)

    @Query("SELECT * FROM people")
    suspend fun getCharacters(): List<PeopleEntity>

    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getCharacterById(id: Int): PeopleEntity

    @Query("DELETE FROM people")
    suspend fun clearCharacters()

    @Query("SELECT COUNT(1) FROM people WHERE id = :id")
    suspend fun containsCharacter(id: Int): Int

}