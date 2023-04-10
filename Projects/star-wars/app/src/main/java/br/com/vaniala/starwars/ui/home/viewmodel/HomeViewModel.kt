package br.com.vaniala.starwars.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.core.doOnFailure
import br.com.vaniala.starwars.core.doOnLoading
import br.com.vaniala.starwars.core.doOnSuccess
import br.com.vaniala.starwars.domain.usecase.GetCategoriesUseCase
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

    private val _categories = MutableStateFlow<State>(State.Loading)
    val categories = _categories.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        getCategoriesUseCase()
            .doOnSuccess {
                val result = if (it.isEmpty()) State.Empty else State.Success(it)
                _categories.emit(result)
            }
            .doOnFailure {
                _categories.emit(State.Error(it?.message ?: "error"))
            }
            .doOnLoading {
                _categories.emit(State.Loading)
            }.collect()
    }
}
