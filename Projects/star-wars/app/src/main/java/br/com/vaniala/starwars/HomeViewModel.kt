package br.com.vaniala.starwars.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.starwars.data.remote.service.doOnFailure
import br.com.vaniala.starwars.data.remote.service.doOnLoading
import br.com.vaniala.starwars.data.remote.service.doOnSuccess
import br.com.vaniala.starwars.domain.usecase.GetCategoriesUseCase
import br.com.vaniala.starwars.ui.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _categories = MutableStateFlow<HomeState>(HomeState.Loading)
    val categories = _categories.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        getCategoriesUseCase()
            .doOnSuccess {
                _categories.emit(HomeState.Success(it))
            }
            .doOnFailure {
                _categories.emit(HomeState.Error(it?.message ?: "error"))
            }
            .doOnLoading {
                _categories.emit(HomeState.Loading)
            }.collect()
    }
}
