package br.com.hurbandroidchallenge.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.domain.model.Categories
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

    private val _homeCategories = MutableStateFlow<StateUI<List<Categories>>>(StateUI.Idle())
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
                _homeUI.value = homeUI.value.copy(categories = data)
                _homeCategories.emit(StateUI.Processed(data))
            }
        }
    }

}