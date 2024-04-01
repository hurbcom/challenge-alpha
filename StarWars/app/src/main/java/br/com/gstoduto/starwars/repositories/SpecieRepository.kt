package br.com.gstoduto.starwars.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.gstoduto.starwars.database.dao.SpecieDao
import br.com.gstoduto.starwars.database.entities.SpecieEntity
import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.network.model.specie.toSpecieEntity
import br.com.gstoduto.starwars.network.services.SpecieService
import br.com.gstoduto.starwars.preferences.PreferencesKey
import br.com.gstoduto.starwars.util.Constants
import br.com.gstoduto.starwars.util.UtilsStarWars
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SpecieRepository @Inject constructor(
    private val dao: SpecieDao,
    private val service: SpecieService,
    private val dataStore: DataStore<Preferences>
) {
    suspend fun findSpecies(): Flow<List<Specie>> {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                val preferencesFlow: Flow<Preferences> = dataStore.data
                val preferences = preferencesFlow.first()
                val utilsStarWar = UtilsStarWars(dataStore)

                val timestamp = preferences[PreferencesKey.TIMESTAMP_SPECIES] ?: Constants.DEFAULT_TIMESTAMP

                if (utilsStarWar.isCompletedOneHour(timestamp.toLong())) {
                    try {
                        val response = service.findAll()
                        val entities = response.results.map { it.toSpecieEntity() }
                        dao.saveAll(*entities.toTypedArray())
                        val species = dao.findAll()
                        continuation.resume(species)
                    } catch (e: ConnectException) {
                        Log.e("SpecieRepository", "findSpecies: falha ao conectar na API", e)
                        continuation.resumeWithException(e)
                    } catch (e: Exception) {
                        Log.e("SpecieRepository", "Err na busca das especies", e)
                        continuation.resumeWithException(e)
                    }
                    utilsStarWar.updateTimestamp(PreferencesKey.TIMESTAMP_SPECIES)
                } else {
                    val species = dao.findAll()
                    continuation.resume(species)
                }
            }
        }
    }

    fun findSpecie(id: String): Flow<SpecieEntity> {
        return dao.findSpecieById(id)
    }

    suspend fun addToMyList(id: String) {
        dao.addToMyList(id)
    }

    suspend fun removeFromMyList(id: String) {
        dao.removeFromMyList(id)
    }

    fun myList(): Flow<List<SpecieEntity>>{
        return dao.myList()
    }
}
