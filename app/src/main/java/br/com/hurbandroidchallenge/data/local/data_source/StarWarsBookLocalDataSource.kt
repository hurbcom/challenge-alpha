package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.data.local.model.UpdateEntity

interface StarWarsBookLocalDataSource <T> {

    suspend fun getEntities(): List<T>

    suspend fun getEntityById(id: Int): T

    suspend fun containsEntity(id: Int): Boolean

    suspend fun insertEntities(characters: List<T>)

    suspend fun clearEntities()

    suspend fun getFavoriteEntities(): List<T>

    suspend fun getLastSeenEntities(): List<T>

    suspend fun updateEntity(entity: UpdateEntity): T

}