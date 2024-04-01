package br.com.gstoduto.starwars.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.gstoduto.starwars.database.dao.MovieDao
import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.network.model.movie.toMovieEntity
import br.com.gstoduto.starwars.network.services.MovieService
import br.com.gstoduto.starwars.preferences.PreferencesKey.TIMESTAMP_MOVIES
import br.com.gstoduto.starwars.util.Constants.DEFAULT_TIMESTAMP
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

class MovieRepository @Inject constructor(
    private val dao: MovieDao,
    private val service: MovieService,
    private val dataStore: DataStore<Preferences>
) {
    suspend fun findMovies(): Flow<List<Movie>> {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                val preferencesFlow: Flow<Preferences> = dataStore.data
                val preferences = preferencesFlow.first()
                val utilsStarWar = UtilsStarWars(dataStore)

                val timestamp = preferences[TIMESTAMP_MOVIES] ?: DEFAULT_TIMESTAMP

                if (utilsStarWar.isCompletedOneHour(timestamp.toLong())) {
                    try {
                        val response = service.findAll()
                        val entities = response.results.map { it.toMovieEntity() }
                        dao.saveAll(*entities.toTypedArray())
                        val movies = dao.findAll()
                        continuation.resume(movies)
                    } catch (e: ConnectException) {
                        Log.e("MovieRepository", "findMovies: falha ao conectar na API", e)
                        continuation.resumeWithException(e)
                    } catch (e: Exception) {
                        Log.e("MovieRepository", "Err na busca dos filmes", e)
                        continuation.resumeWithException(e)
                    }
                    utilsStarWar.updateTimestamp(TIMESTAMP_MOVIES)
                } else {
                    val movies = dao.findAll()
                    continuation.resume(movies)
                }
            }
        }
    }

    fun findMovie(id: String): Flow<MovieEntity> {
        return dao.findMovieById(id)
    }

    suspend fun addToMyList(id: String) {
        dao.addToMyList(id)
    }

    suspend fun removeFromMyList(id: String) {
        dao.removeFromMyList(id)
    }

    fun myList(): Flow<List<MovieEntity>> {
        return dao.myList()
    }
}
