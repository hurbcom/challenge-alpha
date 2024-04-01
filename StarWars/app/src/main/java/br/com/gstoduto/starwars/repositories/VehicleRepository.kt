package br.com.gstoduto.starwars.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.gstoduto.starwars.database.dao.VehicleDao
import br.com.gstoduto.starwars.database.entities.VehicleEntity
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.network.model.vehicle.toVehicleEntity
import br.com.gstoduto.starwars.network.services.VehicleService
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

class VehicleRepository @Inject constructor(
    private val dao: VehicleDao,
    private val service: VehicleService,
    private val dataStore: DataStore<Preferences>
) {
    suspend fun findVehicles(): Flow<List<Vehicle>> {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                val preferencesFlow: Flow<Preferences> = dataStore.data
                val preferences = preferencesFlow.first()
                val utilsStarWar = UtilsStarWars(dataStore)

                val timestamp = preferences[PreferencesKey.TIMESTAMP_VEHICLES] ?: Constants.DEFAULT_TIMESTAMP

                if (utilsStarWar.isCompletedOneHour(timestamp.toLong())) {
                    try {
                        val response = service.findAll()
                        val entities = response.results.map { it.toVehicleEntity() }
                        dao.saveAll(*entities.toTypedArray())
                        val vehicles = dao.findAll()
                        continuation.resume(vehicles)
                    } catch (e: ConnectException) {
                        Log.e("VehicleRepository", "findVehicles: falha ao conectar na API", e)
                        continuation.resumeWithException(e)
                    } catch (e: Exception) {
                        Log.e("VehicleRepository", "Err na busca dos veiculos", e)
                        continuation.resumeWithException(e)
                    }
                    utilsStarWar.updateTimestamp(PreferencesKey.TIMESTAMP_VEHICLES)
                } else {
                    val vehicles = dao.findAll()
                    continuation.resume(vehicles)
                }
            }
        }
    }

    fun findVehicle(id: String): Flow<VehicleEntity> {
        return dao.findVehicleById(id)
    }

    suspend fun addToMyList(id: String) {
        dao.addToMyList(id)
    }

    suspend fun removeFromMyList(id: String) {
        dao.removeFromMyList(id)
    }

    fun myList(): Flow<List<VehicleEntity>>{
        return dao.myList()
    }
}
