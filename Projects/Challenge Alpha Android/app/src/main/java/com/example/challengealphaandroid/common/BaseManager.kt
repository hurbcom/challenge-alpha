package com.example.challengealphaandroid.common

import com.example.challengealphaandroid.model.Planet
import com.example.challengealphaandroid.model.ResultWithStatus
import kotlinx.coroutines.flow.Flow

abstract class BaseManager {
    abstract suspend fun getAllItens(): Flow<ResultWithStatus<List<*>>>

    fun updatePeopleFavorites(
        cache: List<BaseModel>,
        remote: List<BaseModel>
    ): List<BaseModel> {
        return remote.map { postB ->
            val postA = cache.find { it.name == postB.name }
            if (postA != null) {
                postB.apply {
                    isFavorite = postA.isFavorite
                }
            } else {
                postB
            }
        }
    }
}