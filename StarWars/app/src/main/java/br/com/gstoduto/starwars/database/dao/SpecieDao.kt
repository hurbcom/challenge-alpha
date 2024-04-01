package br.com.gstoduto.starwars.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.gstoduto.starwars.database.entities.SpecieEntity
import br.com.gstoduto.starwars.model.Specie
import kotlinx.coroutines.flow.Flow

@Dao
interface SpecieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: SpecieEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vehicle: SpecieEntity)

    @Query("SELECT * FROM species")
    fun findAll(): Flow<List<Specie>>

    @Query("SELECT * FROM species WHERE inMyList = 1")
    fun myList(): Flow<List<SpecieEntity>>

    @Query("UPDATE species SET inMyList = 0 WHERE id = :id")
    suspend fun removeFromMyList(id: String)

    @Query("UPDATE species SET inMyList = 1 WHERE id = :id")
    suspend fun addToMyList(id: String)

    @Query("SELECT * FROM species WHERE id = :id")
    fun findSpecieById(id: String): Flow<SpecieEntity>

    @Query("SELECT * FROM species WHERE name = :name")
    fun findSpecieByName(name: String): Flow<SpecieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(vararg entities: SpecieEntity)
}