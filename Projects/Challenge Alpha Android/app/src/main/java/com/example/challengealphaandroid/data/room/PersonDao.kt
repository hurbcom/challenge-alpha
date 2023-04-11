package com.example.challengealphaandroid.data.room

import androidx.room.*
import com.example.challengealphaandroid.model.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Query("SELECT * FROM people order by name asc")
    suspend fun getAll(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: Collection<Person>)

    @Query("DELETE FROM people")
    suspend fun deleteAll()

    @Update
    suspend fun update(post:Person)
}