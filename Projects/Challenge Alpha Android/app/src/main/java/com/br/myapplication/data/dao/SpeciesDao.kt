package com.br.myapplication.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.myapplication.data.model.Specie

@Dao
interface SpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: Specie)

    @Query("SELECT * FROM species_table ORDER BY id ASC")
    fun getAllSpecies(): PagingSource<Int, Specie>

    @Query("SELECT * FROM species_table ORDER BY id ASC LIMIT :pageSize OFFSET :offset")
    fun getAllSpeciesPaging(offset: Int, pageSize: Int): List<Specie>
}