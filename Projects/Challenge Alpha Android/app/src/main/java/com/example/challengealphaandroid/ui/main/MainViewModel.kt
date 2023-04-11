package com.example.challengealphaandroid.ui.main

import androidx.lifecycle.viewModelScope
import com.example.challengealphaandroid.common.BaseManager
import com.example.challengealphaandroid.common.BaseViewModel
import com.example.challengealphaandroid.domain.PeopleManager
import com.example.challengealphaandroid.domain.PlanetManager
import com.example.challengealphaandroid.domain.StarshipManager
import com.example.challengealphaandroid.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val peopleManager: PeopleManager,
    private val starshipManager: StarshipManager,
    private val planetManager: PlanetManager
) : BaseViewModel<UiStateMain>() {

    private var listPersonFull: List<Person>? = null
    private var listPlanetFull: List<Planet>? = null
    private var listStarshipFull: List<Starship>? = null

    init {
        loadAll()
    }

    private suspend fun fetchItems(
        manager: BaseManager,
        updateFunction: (UiStateMain, List<*>?) -> UiStateMain
    ) {
        manager.getAllItens().collect { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    mutableState.update {
                        updateFunction(it, result.data)
                    }
                }
                Status.ERROR -> {
                    mutableState.update {
                        it.copy(error = result.message)
                    }
                }
            }
        }
    }

    private fun fetchPeople(){
        viewModelScope.launch {
            fetchItems(peopleManager) { uiState, peopleList ->
                listPersonFull = peopleList as? List<Person> ?: emptyList()
                uiState.copy(peopleList = peopleList as? List<Person> ?: emptyList())
            }
        }
    }

    private fun fetchStarships(){
        viewModelScope.launch {
            fetchItems(starshipManager) { uiState, starshipList ->
                listStarshipFull = starshipList as? List<Starship> ?: emptyList()
                uiState.copy(starshipList = starshipList as? List<Starship> ?: emptyList())
            }
        }
    }

    private fun fetchPlanets(){
        viewModelScope.launch {
            fetchItems(planetManager) { uiState, planetList ->
                listPlanetFull = planetList as? List<Planet> ?: emptyList()
                uiState.copy(planetList = planetList as? List<Planet> ?: emptyList())
            }
        }
    }

    fun updatePersonFavorite(person: Person, isFavorite: Boolean) {
        viewModelScope.launch {
            peopleManager.updatePersonFavorite(person, isFavorite)
        }
    }

    fun updateStarshipFavorite(starship: Starship, isFavorite: Boolean) {
        viewModelScope.launch {
            starshipManager.updateStarshipFavorite(starship, isFavorite)
        }
    }

    fun updatePlanetFavorite(planet: Planet, isFavorite: Boolean) {
        viewModelScope.launch {
            planetManager.updatePlanetFavorite(planet, isFavorite)
        }
    }

    fun loadFromCache() {
        viewModelScope.launch {
            val cachePeople = peopleManager.loadCache()
            val cacheStarship = starshipManager.loadCache()
            val cachePlanet = planetManager.loadCache()
            mutableState.update {
                if(cachePeople.isNotEmpty() || cacheStarship.isNotEmpty() || cachePlanet.isNotEmpty())
                    it.copy(peopleList = cachePeople, starshipList = cacheStarship, planetList = cachePlanet, error = null)
                else
                    it.copy(error = "no cache available")
            }
        }
    }

    fun filterPeople(filter: String) {
        if(filter.isEmpty()){
            mutableState.update {
                it.copy(peopleList = listPersonFull)
            }
        } else {
            val filteredPeople = listPersonFull?.filter { it.name.contains(filter, true) }
            mutableState.update {
                it.copy(peopleList = filteredPeople)
            }
        }
    }

    fun filterStarship(filter: String) {
        if(filter.isEmpty()){
            mutableState.update {
                it.copy(starshipList = listStarshipFull)
            }
        } else {
            val filteredStarship = listStarshipFull?.filter { it.name.contains(filter, true) }
            mutableState.update {
                it.copy(starshipList = filteredStarship)
            }
        }
    }

    fun filterPlanet(filter: String) {
        if(filter.isEmpty()){
            mutableState.update {
                it.copy(planetList = listPlanetFull)
            }
        } else {
            val filteredPlanet = listPlanetFull?.filter { it.name.contains(filter, true) }
            mutableState.update {
                it.copy(planetList = filteredPlanet)
            }
        }
    }


    override fun defaultUiState(): UiStateMain {
        return UiStateMain(null)
    }

    private fun loadAll(){
        fetchPeople()
        fetchStarships()
        fetchPlanets()
    }

}

data class UiStateMain(
    val peopleList: List<Person>? = null,
    val starshipList: List<Starship>? = null,
    val planetList: List<Planet>? = null,
    val backupFilterPeopleList: List<Person>? = null,
    val backupFilterStarshipList: List<Starship>? = null,
    val backupFilterPlanetList: List<Planet>? = null,
    val error: String? = null
){
    val inProgress: Boolean = if ((peopleList != null && starshipList != null && planetList != null) || error != null) false else true
}