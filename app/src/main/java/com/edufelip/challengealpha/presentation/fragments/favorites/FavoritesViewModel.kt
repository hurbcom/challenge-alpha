package com.edufelip.challengealpha.presentation.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.usecases.DeleteFavoriteUseCase
import com.edufelip.challengealpha.domain.usecases.GetFavoritesListUseCase
import com.edufelip.challengealpha.presentation.base.models.Event
import com.edufelip.challengealpha.presentation.base.models.StateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _favoritesStateList: MutableStateFlow<Event<StateUI<List<Favorite>>>> = MutableStateFlow(Event(StateUI.Idle()))
    val favoritesStateList = _favoritesStateList.asStateFlow()

    private val _deleteFavoriteState: MutableStateFlow<Event<StateUI<Unit>>> = MutableStateFlow(Event(StateUI.Idle()))
    val deleteFavoriteState = _deleteFavoriteState.asStateFlow()

    fun getFavoritesList() {
        viewModelScope.launch {
            getFavoritesListUseCase()
                .onStart {
                    _favoritesStateList.emit(Event(StateUI.Processing()))
                }
                .catch {
                    _favoritesStateList.emit(Event(StateUI.Error()))
                }
                .collect {
                    _favoritesStateList.emit(Event(StateUI.Processed(it)))
                }
        }
    }

    fun deleteFavoriteState(favorite: Favorite) {
        viewModelScope.launch {
            deleteFavoriteUseCase(favorite)
                .onStart {
                    _deleteFavoriteState.emit(Event(StateUI.Processing()))
                }
                .catch {
                    _deleteFavoriteState.emit(Event(StateUI.Error()))
                }
                .collect {
                    _deleteFavoriteState.emit(Event(StateUI.Processed(Unit)))
                }
        }
    }
}