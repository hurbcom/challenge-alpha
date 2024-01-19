package br.com.mdr.starwars.ui.presentation.favorites

import br.com.mdr.starwars.domain.model.Favorite
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.usecase.FavoritesUseCase
import br.com.mdr.starwars.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single

class FavoritesViewModel (
    private val useCase: FavoritesUseCase,
) : BaseViewModel() {
    private val _favorites = MutableStateFlow<PageState<Favorite>>(PageState.Loading)
    val favorites: StateFlow<PageState<Favorite>>
        get() = _favorites

    init {
        getFavorites()
    }

    override suspend fun refresh() {
        getFavorites()
    }

    private fun getFavorites() {
        launch {
            _favorites.emit(useCase.getFavorites().single())
        }
    }
}