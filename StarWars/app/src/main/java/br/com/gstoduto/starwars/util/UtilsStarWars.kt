package br.com.gstoduto.starwars.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UtilsStarWars (
    private val dataStore: DataStore<Preferences>,
) {
    fun isCompletedOneHour(startTime: Long): Boolean {
        val finalTime = System.currentTimeMillis()
        val timeDifference = finalTime - startTime
        val desiredTime = 60 * 60 * 1000 // 1 hora em milissegundos
        return timeDifference >= desiredTime
    }

    suspend fun updateTimestamp(timeStamp: Preferences.Key<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                it[timeStamp] = System.currentTimeMillis().toString()
            }
        }
    }
}