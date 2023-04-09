package br.com.hurbandroidchallenge.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.data.remote.config.ImageUrls
import br.com.hurbandroidchallenge.domain.model.Category
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
    private val getHomeCategoriesUseCase: GetHomeCategoriesUseCase
) : ViewModel() {

    private val _homeUI = mutableStateOf(HomeUI())
    val homeUI: State<HomeUI> = _homeUI

    private val _homeCategories = MutableStateFlow<StateUI<List<Category>>>(StateUI.Idle())
    val homeCategories = _homeCategories.asStateFlow()

    init {
        getHomeCategories()
    }

    private fun getHomeCategories() {
        viewModelScope.launch {
            getHomeCategoriesUseCase().onStart {
                _homeCategories.emit(StateUI.Processing())
            }.catch {
                _homeCategories.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val categories = getCategoriesList(data)
                _homeUI.value = homeUI.value.copy(categories = categories)
                _homeCategories.emit(StateUI.Processed(categories))
            }
        }
    }

    private fun getCategoriesList(categories: HomeCategories) = listOf(
        Category(
            imageUrl = "${ImageUrls.CategoriesImageBaseUrl}/character.jpg",
            url = categories.people,
            nameRes = R.string.home_categories_people,
        ),
        Category(
            imageUrl = "${ImageUrls.CategoriesImageBaseUrl}/films.jpg",
            url = categories.films,
            nameRes = R.string.home_categories_films
        ),
        Category(
            imageUrl = "${ImageUrls.CategoriesImageBaseUrl}/species.jpg",
            url = categories.species,
            nameRes = R.string.home_categories_species,
        ),
        Category(
            imageUrl = "${ImageUrls.CategoriesImageBaseUrl}/starships.jpg",
            url = categories.starships,
            nameRes = R.string.home_categories_starships,
        ),
        Category(
            imageUrl = "${ImageUrls.CategoriesImageBaseUrl}/vehicles.jpg",
            url = categories.vehicles,
            nameRes = R.string.home_categories_vehicles,
        ),
        Category(
            imageUrl = "${ImageUrls.CategoriesImageBaseUrl}/planets.jpg",
            url = categories.planets,
            nameRes = R.string.home_categories_planets,
        )
    )

}