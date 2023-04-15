package br.com.hurbandroidchallenge.data.repository

import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.extension.pagedListOf
import br.com.hurbandroidchallenge.commom.mapper.NullableListMapper
import br.com.hurbandroidchallenge.commom.mapper.PagedListMapper
import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSource
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper
import br.com.hurbandroidchallenge.data.mapper.planets.toEntity
import br.com.hurbandroidchallenge.data.mapper.planets.toPlanet
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.model.PlanetDto
import br.com.hurbandroidchallenge.data.remote.util.NetworkManager
import br.com.hurbandroidchallenge.data.remote.util.apiCall
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlanetsRepository(
    private val remoteDataSource: StarWarsBookRemoteDataSource,
    private val localDataSource: StarWarsBookLocalDataSource,
    private val planetDtoToEntityMapper: PagedListMapper<PlanetDto, PlanetEntity>,
    private val planetEntityToPlanetMapper: NullableListMapper<PlanetEntity, Planet>,
    private val networkManager: NetworkManager
) : StarWarsBookRepository<Planet> {

    private val preferences = PreferencesWrapper.getInstance()

    override fun getItemList(url: String, clearLocalDatasource: Boolean): Flow<PagedList<Planet>> {
        return flow {
            if (clearLocalDatasource) localDataSource.clearPlanets()
            if (preferences.isPlanetsUpToDate()) {
                emit(pagedListOf(getLocalPlanets()))
            } else if (networkManager.hasInternetConnection()) {
                apiCall {
                    val remotePlanets = remoteDataSource.getPlanets(url)
                    localDataSource.insertPlanets(planetDtoToEntityMapper.map(remotePlanets).results)
                    if (remotePlanets.next == null)
                        preferences.planetsIsUpToDate()
                    emit(
                        PagedList(
                            next = remotePlanets.next,
                            previous = remotePlanets.previous,
                            results = getLocalPlanets()
                        )
                    )
                }
            } else {
                emit(pagedListOf(getLocalPlanets()))
            }
        }
    }

    override fun getItemByUrl(url: String): Flow<Planet> {
        return flow {
            if (localDataSource.containsCharacter(url.idFromUrl())) {
                emit(getLocalCharacterByUrl(url))
            } else if (networkManager.hasInternetConnection()) {
                val remotePlanets = remoteDataSource.getPlanetByUrl(url)
                localDataSource.insertPlanets(listOf(remotePlanets.toEntity()))
                val localPlanet = getLocalCharacterByUrl(remotePlanets.url.orEmpty())
                emit(localPlanet)
            } else {
                getLocalCharacterByUrl(url)
            }
        }
    }

    override fun getFavoriteItems(): Flow<List<Planet>> {
        return flow { emit(planetEntityToPlanetMapper.map(localDataSource.getFavoritePlanets())) }
    }

    override fun getLastSeenItems(): Flow<List<Planet>> {
        return flow { emit(planetEntityToPlanetMapper.map(localDataSource.getLastSeenPlanets())) }
    }

    override fun updateItem(item: Planet): Flow<Unit> {
        return flow { emit(localDataSource.updatePlanet(item.toEntity())) }
    }

    private suspend fun getLocalPlanets() =
        planetEntityToPlanetMapper.map(localDataSource.getPlanets())

    private suspend fun getLocalCharacterByUrl(url: String) =
        localDataSource.getPlanetById(url.idFromUrl()).toPlanet()

}