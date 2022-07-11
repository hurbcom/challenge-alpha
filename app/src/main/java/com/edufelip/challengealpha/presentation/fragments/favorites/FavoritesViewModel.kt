package com.edufelip.challengealpha.presentation.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.usecases.GetFavoritesListUseCase
import com.edufelip.challengealpha.presentation.base.models.StateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesListUseCase: GetFavoritesListUseCase
) : ViewModel() {

    private val _favoritesStateList: MutableStateFlow<StateUI<List<Favorite>>> = MutableStateFlow(StateUI.Idle())
    val favoritesStateList = _favoritesStateList.asStateFlow()

    init {
        getFavoritesList()
    }

    private fun getFavoritesList() {
        viewModelScope.launch {
            getFavoritesListUseCase()
                .onStart {
                    _favoritesStateList.emit(StateUI.Processing())
                }
                .catch {
                    _favoritesStateList.emit(StateUI.Error())
                }
                .collect {
                    _favoritesStateList.emit(StateUI.Processed(it))
                }
        }
    }
}