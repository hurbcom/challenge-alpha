package br.com.gstoduto.starwars.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.gstoduto.starwars.database.entities.VehicleEntity
import br.com.gstoduto.starwars.model.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: VehicleEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vehicle: VehicleEntity)

    @Query("SELECT * FROM vehicles")
    fun findAll(): Flow<List<Vehicle>>

    @Query("SELECT * FROM vehicles WHERE inMyList = 1")
    fun myList(): Flow<List<VehicleEntity>>

    @Query("UPDATE vehicles SET inMyList = 0 WHERE id = :id")
    suspend fun removeFromMyList(id: String)

    @Query("UPDATE vehicles SET inMyList = 1 WHERE id = :id")
    suspend fun addToMyList(id: String)

    @Query("SELECT * FROM vehicles WHERE id = :id")
    fun findVehicleById(id: String): Flow<VehicleEntity>

    @Query("SELECT * FROM vehicles WHERE name = :name")
    fun findVehicleByName(name: String): Flow<VehicleEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(vararg entities: VehicleEntity)
}