package br.com.hurbandroidchallenge.presentation.screens.home

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.commom.extension.getString
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.domain.model.HomeCategories
import br.com.hurbandroidchallenge.domain.use_case.GetHomeCategoriesUseCase
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.home.ui.HomeUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeListViewModel(
    private val getHomeCategoriesUseCase: GetHomeCategoriesUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _homeUI = mutableStateOf(HomeUI())
    val homeUI: State<HomeUI> = _homeUI

    private val _homeCategories = MutableStateFlow<StateUI<List<ItemModel>>>(StateUI.Idle)
    val homeCategories = _homeCategories.asStateFlow()

    init {
        getHomeCategories()
    }

    private fun getHomeCategories() {
        viewModelScope.launch {
            getHomeCategoriesUseCase().onStart {
                _homeCategories.emit(StateUI.Processing)
            }.catch {
                _homeCategories.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val categories = getCategoriesList(data)
                _homeUI.value = homeUI.value.copy(categories = categories)
                _homeCategories.emit(StateUI.Processed)
            }
        }
    }

    private fun getCategoriesList(categories: HomeCategories) = listOf(
        ItemModel(
            image = "${ApiUrls.imageBaseUrl}/categories/character.jpg",
            url = categories.people,
            name = getString(R.string.home_categories_people)
        ),
        ItemModel(
            image = "${ApiUrls.imageBaseUrl}/categories/films.jpg",
            url = categories.films,
            name = getString(R.string.home_categories_films)
        ),
        ItemModel(
            image = "${ApiUrls.imageBaseUrl}/categories/species.jpg",
            url = categories.species,
            name = getString(R.string.home_categories_species)
        ),
        ItemModel(
            image = "${ApiUrls.imageBaseUrl}/categories/starships.jpg",
            url = categories.starships,
            name = getString(R.string.home_categories_starships)
        ),
        ItemModel(
            image = "${ApiUrls.imageBaseUrl}/categories/vehicles.jpg",
            url = categories.vehicles,
            name = getString(R.string.home_categories_vehicles)
        ),
        ItemModel(
            image = "${ApiUrls.imageBaseUrl}/categories/planets.jpg",
            url = categories.planets,
            name = getString(R.string.home_categories_planets)
        )
    )

}