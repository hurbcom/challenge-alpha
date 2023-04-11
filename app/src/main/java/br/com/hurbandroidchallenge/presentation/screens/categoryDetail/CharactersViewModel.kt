package br.com.hurbandroidchallenge.presentation.screens.categoryDetail

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import br.com.hurbandroidchallenge.domain.use_case.GetCharactersUseCase
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.categoryDetail.ui.CharactersUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCategoryUseCase: GetCharactersUseCase,
    application: Application,
) : AndroidViewModel(application) {

    private val _charactersUI = mutableStateOf(CharactersUI())
    val categoryUI: State<CharactersUI> = _charactersUI

    private val _categoryItems = MutableStateFlow<StateUI<PagedList<People>>>(StateUI.Idle)
    val categoryItems = _categoryItems.asStateFlow()

    private val _loadMoreCategoryItems = MutableStateFlow<StateUI<PagedList<People>>>(StateUI.Idle)
    val loadMoreCategoryItems = _loadMoreCategoryItems.asStateFlow()

    init {
        loadCategoryItems()
    }

    private fun loadCategoryItems() {
        viewModelScope.launch {
            getCategoryUseCase(url = ApiUrls.characters).onStart {
                _categoryItems.emit(StateUI.Processing)
            }.catch {
                _categoryItems.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _charactersUI.value = categoryUI.value.copy(
                    categoryItems = data.results,
                    nextPage = data.next
                )
                _categoryItems.emit(StateUI.Processed)
            }
        }
    }

    fun loadMoreCategoryItems() {
        viewModelScope.launch {
            _charactersUI.value.nextPage?.let { nextPage ->
                getCategoryUseCase(url = nextPage).onStart {
                    _categoryItems.emit(StateUI.Processing)
                }.catch {
                    _categoryItems.emit(StateUI.Error(it.message.orEmpty()))
                }.collect { data ->
                    _charactersUI.value = categoryUI.value.copy(
                        categoryItems = categoryUI.value.categoryItems.plus(data.results),
                        nextPage = data.next
                    )
                    _categoryItems.emit(StateUI.Processed)
                }
            }
        }
    }

}