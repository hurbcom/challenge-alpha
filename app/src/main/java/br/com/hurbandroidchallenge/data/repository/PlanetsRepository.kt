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

    override fun getItemList(url: String): Flow<PagedList<Planet>> {
        return flow {
            if (preferences.isPlanetsUpToDate()) {
                emit(pagedListOf(getLocalPlanets()))
            } else if (networkManager.hasInternetConnection()) {
                apiCall {
                    val remotePlanets = remoteDataSource.getPlanets(url)
                    localDataSource.updatePlanets(planetDtoToEntityMapper.map(remotePlanets).results)
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
                localDataSource.updatePlanets(listOf(remotePlanets.toEntity()))
                emit(remotePlanets.toPlanet())
            } else {
                getLocalCharacterByUrl(url)
            }
        }
    }

    private suspend fun getLocalPlanets() =
        planetEntityToPlanetMapper.map(localDataSource.getPlanets())

    private suspend fun getLocalCharacterByUrl(url: String) =
        localDataSource.getPlanetById(url.idFromUrl()).toPlanet()

}