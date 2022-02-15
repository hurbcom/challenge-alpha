package com.isranascimento.databasedtos.hotels

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "hotels")
data class HotelDatabaseEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "insertedTime")
    @NonNull
    val insertedTime: Long,

    /**
     * Creio que no cenário ideal, somente as duas informações acima seriam salvas
     * e quem fosse montar a célula do hotel lidaria com a requisição web para montar a célula a partir
     * do id. Como não existe essa requisição à partir do mocky, resolvi colocar os outros campos aqui
     * também para montar a célula do hotel e a tela de detalhes do mesmo.
     */

    @ColumnInfo(name = "city")
    @NonNull
    val city: String,

    @ColumnInfo(name = "state")
    @NonNull
    val state: String,

    @ColumnInfo(name = "mainImage")
    @NonNull
    val mainImage: String,

    @ColumnInfo(name = "name")
    @NonNull
    val name: String,
    
    @ColumnInfo(name = "starCount")
    @NonNull 
    val starCount: Int,
    
    @ColumnInfo(name = "description")
    @NonNull 
    val description: String,

    @ColumnInfo(name = "url")
    @NonNull
    val url: String,
)