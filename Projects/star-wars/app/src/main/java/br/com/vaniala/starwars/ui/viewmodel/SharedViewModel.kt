package br.com.vaniala.starwars.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.core.doOnFailure
import br.com.vaniala.starwars.core.doOnLoading
import br.com.vaniala.starwars.core.doOnSuccess
import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _categories = MutableStateFlow<State<List<Category>>>(State.Loading)
    val categories: StateFlow<State<List<Category>>>
        get() = _categories

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

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
            }.apply {
                _isLoading.emit(false)
            }.collect()
    }
}
